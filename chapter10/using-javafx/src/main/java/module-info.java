/**
 * Created by iuliana.cosmina on 3/6/18.
 */
module chapter.ten.javafx {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    opens com.apress.bgn.ch10 to javafx.graphics;
}