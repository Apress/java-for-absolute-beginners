/**
 * Created by iuliana.cosmina on 1/14/18.
 * If you decomment the open declarative, the Main method in chapter01 will no longer fail with InaccessibleObjectException
 */
/*open*/ module chapter.zero {
    requires transitive org.apache.logging.log4j;

    //allows reflective access to chapter.three module, to private members of package com.apress.bgn.ch0
    opens com.apress.bgn.ch0 to chapter.three;

    //allows access to public public members in package com.apress.bgn.ch0 to modules: chapter.one, chapter.three, chapter.four
    exports com.apress.bgn.ch0 to chapter.one, chapter.three, chapter.four;

    //allows access to public public members in package com.apress.bgn.ch0.service
    exports com.apress.bgn.ch0.service;
}