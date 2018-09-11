/*
Freeware License, some rights reserved

Copyright (c) 2018 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ch13;

import com.apress.bgn.ch13.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Random;

import static com.apress.bgn.ch13.util.MemAudit.printTotalMemory;
import static com.apress.bgn.ch13.util.MemAudit.printBusyMemory;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static NameGenerator nameGenerator = new NameGenerator();
    private static final Random random = new Random();

    public static void main(String... args) {
        printTotalMemory(log);
        int count =0;
        while (true) {
            genSinger();
            count++;
            if (count % 1000 == 0) {
                printBusyMemory(log);
                System.gc();
                //Runtime.getRuntime().gc();
            }
        }
    }

    private static void genSinger() {
        Singer s = new Singer(nameGenerator.genName(), random.nextDouble(), LocalDate.now());
        log.info("JVM created: {}", s.getName());
    }

}
