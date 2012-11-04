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
import org.apache.log4j.PropertyConfigurator
import static org.pm.Utilities.*

class Glucose {
	private static Logger logger = Logger.getLogger(Glucose.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		logger.debug("Number of arguments passed - " + args.size());
		
		if (System.getProperty("env") == null || System.getProperty("task") == null ||
            System.getProperty("env").length() == 0 || System.getProperty("task").length() == 0) {
			System.out.println ("Missing command. To use - ");
			System.out.println ("gradle run -Denv=local -Dtask=Utilities.uptime");
			System.out.println ("gradle run -Denv=local -Dtask=Utilities.runCommand -Dargs=\"ls\" ");
            System.out.println ("gradle run -Denv=local -Dtask=Utilities.runCommand -Dargs=\"ps -ef | grep `whoami`\" ");
			System.exit(1)
		}
		
		
        String environmentName = System.getProperty("env")
        logger.debug("environmentName - " + environmentName)

        String classFunctionName = System.getProperty("task")
		logger.trace("classFunctionName - " + classFunctionName)
		
		//Split the name of class and function
		String[] split=classFunctionName.split("\\.")
		String className = split[0]
		logger.trace("className - " + className)
		
		String action = split[1]
		logger.trace("action - " + action)
		
		logger.debug("First 2 arguments processed. About to load environment class - " + environmentName)
		
		//Dynamically load class
		Utilities.environment = Class.forName("org.pm.environments." + environmentName, true, this.classLoader)
		logger.trace(Utilities.environment.toString())
		
		def classAction = Class.forName("org.pm." + className, true, this.classLoader)
		logger.trace(classAction.toString())

        //Example ./run.sh Utilities.uptime
        if (System.getProperty("args") == null || System.getProperty("args").length() == 0)  {
			logger.trace("About to run " + classAction + "." + action + "()")
			
			classAction."${action}"()
			
			logger.trace("Completed " + classAction + "." + action + "()")
		} else {
			String arguments = System.getProperty("args")
			
			logger.trace("About to run " + classAction + "." + action + "(" + arguments + ")")
			
			//Dynamically call function
			classAction."${action}"(arguments)
			
			logger.trace("Completed " + classAction + "." + action + "(" + arguments + ")")
		}
	}
}