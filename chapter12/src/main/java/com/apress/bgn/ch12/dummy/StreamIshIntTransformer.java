package com.apress.bgn.ch12.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by iuliana.cosmina on 3/6/18
 */

//-Dlogback.configuration=src/test/resources/logback.xml
public class StreamIshIntTransformer {
    private static final Logger log = LoggerFactory.getLogger(StreamIshIntTransformer.class);
    private static final Random random = new Random();

    public static void main(String... args) {
        IntStream intStream = IntStream.generate(() -> random.nextInt(130));

    /*    intStream.filter(rndNo -> rndNo >= 0 && rndNo < 127).forEach(rndNo -> {
            if (rndNo % 2 == 0 && rndNo >= 98 && rndNo <= 122) {
                rndNo -= 32;
            }
            char res = (char) rndNo;
            log.info("Result: {}", res);
        });*/

        intStream.filter(rndNo -> rndNo >= 0 && rndNo < 127
                && (rndNo % 2 == 0 && rndNo >= 98 && rndNo <= 122))
                .mapToObj(rndNo -> ((char) (rndNo - 32)))
                .forEach(res -> log.info("Result: {}", res));
    }
}
