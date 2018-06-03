package com.apress.bgn.ch1;

import com.apress.bgn.ch0.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;

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
        } catch (InaccessibleObjectException ioe) {
            LOGGER.error("Field 'secret' cannot be accessed!");
        } catch (NoSuchFieldException nsf) {
            LOGGER.error("Field 'secret' does not exist!");
        } catch (IllegalAccessException e) {
            LOGGER.error("Field 'secret' cannot be set!");
        }
    }
}
