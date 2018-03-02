package com.apress.bgn.ch0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 2/25/18
 */
public class Base {

    private static Logger LOGGER = LoggerFactory.getLogger(Base.class);

    private int secret = 0;

    public Base() {
        LOGGER.info("Constructing instance of Base class.");
    }

    /**
     * method to print value of private field secret
     */
    public void printSecret() {
        LOGGER.info("Secret > {}", secret);
    }
}

class HiddenBase{
    // you cannot see me
}

