package com.apress.bgn.ch0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by iuliana.cosmina on 2/25/18
 */
public class Base {

    private static final Logger LOGGER = LogManager.getLogger();

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

class HiddenBase {
    // you cannot see me
}

