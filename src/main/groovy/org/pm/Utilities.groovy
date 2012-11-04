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
import org.pm.environments.IEnvironment

class Utilities {
	static environment;
	private static Logger logger = Logger.getLogger(Utilities.class);
	
	static void runCommand(command) {
		logger.trace("runCommand(" + command + ")")
		
		def ant = new AntBuilder()
		
		for (host in environment.hosts) {
			logger.debug("About to run on " + environment.user + "@" + host + " \"" + command + "\"")
			
			ant.sshexec(host:host,
				keyfile:environment.keyFile,
				username:environment.user,
				command:command)
			
			logger.debug("Completed run on " + host)
		}
	}
	
	static void uptime() {
        logger.info("Running uptime")
		runCommand("uptime")
	}
}
