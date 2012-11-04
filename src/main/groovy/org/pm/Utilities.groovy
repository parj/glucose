/*
Copyright (c) 2011, 2012 Parjanya Mudunuri

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

http://opensource.org/licenses/mit-license.php
 */

package org.pm

import org.apache.log4j.Logger
import org.apache.tools.ant.taskdefs.optional.ssh.SSHExec

class Utilities {
	static environment;
	private static Logger logger = Logger.getLogger(Utilities.class);
	
	static void runCommand(command) {
		logger.trace("runCommand(" + command + ")")

		SSHExec ssh = new SSHExec()
        ssh.setTrust(true)
        try {
            ssh.setOutput(new File("output.log"))
        } catch(IOException err) {
            logger.error("Unable to write to output.log")
        }

		for (host in environment.hosts) {
			logger.debug("About to run on " + environment.user + "@" + host + " \"" + command + "\"")

            ssh.setKnownhosts(host)
            ssh.setCommand(command)
            ssh.setHost(host)
            ssh.setKeyfile(environment.keyFile)
            ssh.setUsername(environment.user)
			ssh.execute()

			logger.debug("Completed run on " + host)
		}
	}
	
	static void uptime() {
		runCommand("uptime")
	}
}
