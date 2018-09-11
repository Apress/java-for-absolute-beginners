/**
 * Created by iuliana.cosmina on 3/5/18.
 */
module chapter.eleven.media {
    requires  java.desktop;
    requires org.slf4j;
    requires common.sanselan;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    opens com.apress.bgn.ch11 to javafx.graphics;
}