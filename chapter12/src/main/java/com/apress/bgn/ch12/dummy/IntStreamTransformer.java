package com.apress.bgn.ch12.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by iuliana.cosmina on 3/6/18
 */

//-Dlogback.configuration=src/test/resources/logback.xml
public class IntStreamTransformer {
    private static final Logger log = LoggerFactory.getLogger(IntStreamTransformer.class);
    private static final Random random = new Random();

    public static void main(String... args) {
        IntStream intStream = IntStream.generate(() -> random.nextInt(130));

        intStream.forEach(rndNo -> {
            if (rndNo >= 0 && rndNo < 127) {
                log.info("Initial value: {} ", rndNo);
                if (rndNo % 2 == 0 && rndNo >= 98 && rndNo <= 122) {
                    rndNo -= 32;
                }
                char res = (char) rndNo;
                log.info("Result: {}", res);
            } else {
                log.debug("Number {} discarded.", rndNo);
            }
        });
    }
}
