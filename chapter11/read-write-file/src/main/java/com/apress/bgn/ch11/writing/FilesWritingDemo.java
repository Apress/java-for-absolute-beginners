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
package com.apress.bgn.ch11.writing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class FilesWritingDemo {
    private static final Logger log = LoggerFactory.getLogger(FilesWritingDemo.class);

    public static void main(String... args) {
        File file = new File("chapter11/read-write-file/src/main/resources/output/vultures.txt");
        log.info("------------------Using File.write(Path, byte[]) ------------------------");

        byte[] data = "Some of us, we're hardly ever here".getBytes();
        try {
            Path dataPath = Files.write(file.toPath(), data);
            log.info("String written to {}", dataPath.toAbsolutePath());
        } catch (IOException e) {
            log.info("Something went wrong! ", e);
        }

        //This is unstable until Gradle supports Java 11
        /*log.info("------------------Using File.writeString(Path, CharSequence) ------------------------");
        try {
            Path dataPath = Files.writeString(file.toPath(),
                    "Some of us, we're hardly ever here", StandardCharsets.UTF_8);
            log.info("String written to {}", dataPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        List<String> dataList =  List.of("Some of us, we're hardly ever here",
                "The rest of us, we're born to disappear",
                "How do I stop myself from",
                "Being just a number?");
        try {
            File file2 = new File("chapter11/read-write-file/src/main/resources/output/vultures2.txt");
            Path dataPath = Files.write(file2.toPath(),  dataList, StandardCharsets.UTF_8);
            log.info("String written to {}", dataPath.toAbsolutePath());
        } catch (IOException e) {
            log.info("Something went wrong! ", e);
        }
    }
}
