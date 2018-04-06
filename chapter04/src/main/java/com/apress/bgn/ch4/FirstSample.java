package com.apress.bgn.ch4;  // package declaration

import org.slf4j.Logger;  // import statements
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

/**
 * This class is a sample containing most Java programming elements.   // javadoc comment
 *
 * @author iuliana.cosmina
 */
public class FirstSample { // [access modifier, object type, name]
    private static Logger LOGGER = LoggerFactory.getLogger(FirstSample.class);  // identifier + comment

    public static void main(String... args) { // []"
        int[] numbers = {7, 4, 3, 9, 5, 100_000};

        for (int i = 0; i < numbers.length; ++i) {
            System.out.println("Item no" + i + " is " + numbers[i]);
        }

        // use this to build a String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] < MAX_VALUE) {
                sb.append("Item no" + i + " is " + numbers[i]).append("\n");
            }
        }
        LOGGER.info(sb.toString());
    }
}
