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
package com.apress.bgn.ch11.reading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.*;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class FilesReadingDemo {
    private static final Logger log = LoggerFactory.getLogger(FilesReadingDemo.class);
    public static void main(String... args) {
        File file = new File("chapter11/read-write-file/src/main/resources/input/vultures.txt");
        log.info("------------------Using Files.readAllBytes ------------------------");
        String content="";
        try {
            content = new String(readAllBytes(Paths.get(file.toURI())));
            log.info("Read with Files.readAllBytes --> {}", content);
        } catch (IOException e) {
            log.info("Something went wrong! ", e);
        }

        //This is unstable until Gradle supports Java 11
        /*log.info("------------------Using Files.readString ------------------------");
        try {
            content = Files.readString(Paths.get(file.toURI()), StandardCharsets.UTF_8);
            log.info("Read with Files.readAllBytes --> {}", content);
        } catch (IOException e) {
            log.info("Something went wrong! ", e);
        }*/


        log.info("------------------Using Files.realAllLines ------------------------");
        try {
            List<String> lyricList = readAllLines(Paths.get(file.toURI()), StandardCharsets.UTF_8)
                    .stream().filter(line -> !line.contains("Ooh")).collect(Collectors.toList());
            lyricList.forEach(System.out::println);
        } catch (IOException e) {
            log.info("Something went wrong! ", e);
        }

        log.info("------------------Using Files.lines ------------------------");
        try {
            Stream<String> lyricStream = lines(file.toPath())
                    .filter(s -> !s.contains("Ooh"));
            lyricStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
