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


import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CdKeyAgentTest
{
    /** Must match key at sKeyFile. */
    private static final String sKeyValue = "XXXX-XXXX-XXXX-XXXX";
    private static final File sKeyFile =
        new File(System.getProperty("user.dir"), "src/test/resources/test.key");


    private class Agent extends CdKeyAgent {
        public Agent(File key) throws FileNotFoundException { super(key); }
        @Override
        public boolean authorized() { return sKeyValue.equals(key()); }
    }


    @Test
    public void example() {

        try {
            CdKeyAgent agent = new Agent(sKeyFile);
            agent.read();
            Assert.assertTrue(agent.authorized());
        } catch (FileNotFoundException ex) {
            Assert.fail(ex.getMessage());
        } catch (IOException ex) {
            Assert.fail(ex.getMessage());
        }

    }

}

