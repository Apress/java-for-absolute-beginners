/**
 * Created by iuliana.cosmina on 1/14/18.
 */
module chapter.one {
    requires chapter.zero;

    provides com.apress.bgn.ch0.service.NakedService with com.apress.bgn.ch1.service.Provider;
}