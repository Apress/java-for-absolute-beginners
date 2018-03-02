package com.apress.bgn.ch1;

import com.apress.bgn.ch0.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;

/**
 * Created by iuliana.cosmina on 2/26/18
 */
public class Main {
    //testing to classes in transitive module slf4j.org
    private static Logger LOGGER = LoggerFactory.getLogger(Base.class);

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
