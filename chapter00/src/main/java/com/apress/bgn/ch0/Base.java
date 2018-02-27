package com.apress.bgn.ch0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 2/25/18
 */
public class Base {

    private static Logger LOGGER = LoggerFactory.getLogger(Base.class);


    public Base() {
        LOGGER.info("Constructing instance of Base class.");
    }
}
