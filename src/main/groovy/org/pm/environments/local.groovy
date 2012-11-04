package org.pm.environments

/**
 * Created with IntelliJ IDEA.
 * User: parj
 * Date: 04/11/2012
 * Time: 09:07
 * To change this template use File | Settings | File Templates.
 */
class local implements IMxEnvironment {
    static MX = "/opt/DEV/parj/thirdparty/murex"
    static hosts = ["localhost"]
    static user = "parj"
    static keyFile = "/Users/parj/.ssh/id_rsa"
}
