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
import static Utilities.*

class Murex {
	private static Logger logger = Logger.getLogger(Murex.class);
	
	static void checkServices() {	
		String command = "./launchmxj.app -s"
		
		logger.trace("About to run runMurexCommand (" + command + ")")
		runMurexCommand(command)	
		logger.trace("Completed runMurexCommand (" + command + ")")
	}
	
	static void start() {
		String command = "./mxg2000_launchall start"
		
		logger.trace("About to run runMurexCommand (" + command + ")")
		runMurexCommand(command)
		logger.trace("Completed runMurexCommand (" + command + ")")
		
		checkServices()
	}
	
	static void stop() {	
		String command = "./mxg2000_launchall stop"
		
		logger.trace("About to run runMurexCommand (" + command + ")")
		runMurexCommand("./mxg2000_launchall stop")		
		logger.trace("Completed runMurexCommand (" + command + ")")
	}
	
	static void runMurexCommand(command) {
		String stringCommand
		
		logger.trace("command - " + command)
		
		//If MX variable is not defined
		try {
			stringCommand = "cd ${Utilities.environment.MX};" + command
		}
		catch (groovy.lang.MissingPropertyException e) {
			stringCommand = "cd \$MX;" + command
		}
		
		logger.trace("stringCommand - " + stringCommand)

		logger.debug("About to run runMurexCommand (" + stringCommand + ")")
		
		runCommand(stringCommand)
		
		logger.debug("Completed runMurexCommand (" + stringCommand + ")")
	}
}
