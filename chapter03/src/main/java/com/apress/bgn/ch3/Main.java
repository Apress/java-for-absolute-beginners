package com.apress.bgn.ch3;

import com.apress.bgn.ch0.Base;
import com.apress.bgn.ch0.service.NakedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ServiceLoader;

/**
 * Created by iuliana.cosmina on 2/26/18
 */
public class Main {
    //testing to classes in transitive module slf4j.org
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String... args) {
        //testing access to Base class from module chapter.zero
        Base base = new Base();
        LOGGER.info("Base object was created? >  {} ", (base != null));

        //testing reflection
        try {
            Field field = base.getClass().getDeclaredField("secret");
            field.setAccessible(true);
            field.set(base, 1);
            base.printSecret();
        } catch (NoSuchFieldException nsf) {
            LOGGER.error("Field 'secret' cannot be accessed!" );
        } catch (IllegalAccessException e) {
            LOGGER.error("Field 'secret' cannot be set!" );
        }

        //testing service
        ServiceLoader<NakedService> loader = ServiceLoader.load(NakedService.class);
        NakedService service = loader.findFirst().get();
        LOGGER.info("Service found: {}, with secret '{}'",service.getClass(), service.theSecret());

    }
}
