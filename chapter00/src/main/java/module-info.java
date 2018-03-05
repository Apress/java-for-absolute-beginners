/**
 * Created by iuliana.cosmina on 1/14/18.
 * If you decomment the open declarative, the Main method in chapter01 will no longer fail with InaccessibleObjectException
 */
/*open*/ module chapter.zero {
    requires transitive org.slf4j;

    opens com.apress.bgn.ch0 to chapter.three;

    exports com.apress.bgn.ch0 to chapter.one, chapter.three;

    exports com.apress.bgn.ch0.service;
}