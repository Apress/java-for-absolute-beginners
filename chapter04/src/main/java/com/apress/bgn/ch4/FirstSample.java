package com.apress.bgn.ch4;  // package declaration

import org.apache.logging.log4j.LogManager;// import statements
import org.apache.logging.log4j.Logger;

import static java.lang.Integer.MAX_VALUE;

/**
 * This class is a sample containing most Java programming elements.   // javadoc comment
 *
 * @author iuliana.cosmina
 */
public class FirstSample { // [access modifier, object type, name]
    private static final Logger LOGGER = LogManager.getLogger();  // identifier + comment

    public static void main(String... args) throws Exception { // []"
        int[] numbers = {7, 4, 3, 9, 5, 100_000};

        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] < MAX_VALUE) {
                LOGGER.info("Item no" + i + " is " + numbers[i]);
            }
        }

    }
}
