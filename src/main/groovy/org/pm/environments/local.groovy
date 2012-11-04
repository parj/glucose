package org.pm.environments


class local implements IMxEnvironment {
    static MX = "/opt/DEV/foo/thirdparty/murex"
    static hosts = ["localhost"]
    static user = "foo"
    static keyFile = "/tmp/foo/.ssh/id_rsa"
}
