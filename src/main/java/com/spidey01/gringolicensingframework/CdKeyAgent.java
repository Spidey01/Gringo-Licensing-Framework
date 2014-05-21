/*-
 * Copyright (c) 2014-current, Terry Mathew Poulin <BigBoss1964@gmail.com>
 *
 * This software is provided 'as-is', without any express or implied warranty.
 * In no event will the authors be held liable for any damages arising from the
 * use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 *	1. The origin of this software must not be misrepresented; you must
 *	   not claim that you wrote the original software. If you use this
 *	   software in a product, an acknowledgment in the product
 *	   documentation would be appreciated but is not required.
 *
 *	2. Altered source versions must be plainly marked as such, and must
 *	   not be misrepresented as being the original software.
 *
 *	3. This notice may not be removed or altered from any source
 *	   distribution.
 */

package com.spidey01.gringolicensingframework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


/** Implements a CD Key based licensing agent.
 *
 * CD Key data should be in the format of a single line of key data. Terminated
 * by '\n' or EOF.
 */
public class CdKeyAgent
    implements LicensingAgent
{
    private String mCdKey = "";
    private InputStream mSource;


    public CdKeyAgent(File key) throws FileNotFoundException {
        mSource = new FileInputStream(key);
    }


    /** Create from URI.
     *
     * @throws IOException D'uh.
     * @throws MalformedURLException Unable to convert to URL.
     */
    public CdKeyAgent(URI keyURI)
        throws MalformedURLException, IOException
    {
        this(keyURI.toURL());
    }


    public CdKeyAgent(URL keyURL) throws IOException {
        mSource = keyURL.openStream();
    }


    /** Get the CD key. */
    public String key() {
        return mCdKey;
    }


    /** Read the users CD Key.
     */
    public void read() throws IOException {
        read(mSource);
    }


    /** Read CD Key from InputStream.
     *
     * This method serves so that you may override it with an implementation
     * that performs whatever monkey business you may require. Default
     * behaviour is to read from the stream.
     *
     * If you have setup a Public Key Infrastructure (PKI) and all that jazz to
     * handle distributing encrpyted CD keys, you would override this to
     * implement decryption.
     *
     * If you need to do some kind of fancy magic like out of band access
     * through a key fob you could probably wrap that here.
     */
    protected void read(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        mCdKey = reader.readLine();
    }



    /** Decide if CD Key is valid and authorized.
     *
     * Override this method to determine if CD Key is valid and authorized. The default is to ...
     */
    @Override
    public boolean authorized() {
        return false;
    }


}

