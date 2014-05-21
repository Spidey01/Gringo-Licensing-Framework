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


/** Implements a class based licensing agent.
 *
 * If the class exists at runtime then the license is valid.
 */
public class ClassAgent
    implements LicensingAgent
{

    private boolean mExists = false;


    public ClassAgent(Class<?> key) {
        mExists = true;
    }


    /** Wrapper for Class.forName().
     *
     * Only ClassNotFoundException is shallowed (authorized() == false). Other
     * issues associated with Class.forName() are the callers concern.
     */
    public ClassAgent(String className) {
        try {
            Class.forName(className);
            mExists = true;
        } catch (ClassNotFoundException ex) {
            mExists = false;
        }
    }


    @Override
    public boolean authorized() {
        return mExists;
    }


}


