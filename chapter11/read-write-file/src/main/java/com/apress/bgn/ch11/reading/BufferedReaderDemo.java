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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class BufferedReaderDemo {

    private static final Logger log = LoggerFactory.getLogger(BufferedReaderDemo.class);

    public static void main(String... args) {
        File file = new File("chapter11/read-write-file/src/main/resources/input/vultures.txt");
        String content = "";

        //before java 7
        /*log.info("------------------Using Files.BufferedReader ------------------------");
        BufferedReader reader = null;
        try {
             reader = new BufferedReader(new FileReader(
                    new File("chapter11/read-write-file/src/main/resources/input/vultures.txt")));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if(!line.contains("Ooh")) {
                    sb.append(line).append("\n");
                }
            }
           log.info("Read with BufferedReader --> {}", sb.toString() );
        } catch (Exception e) {
            log.info("Something went wrong! ", e);
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }*/

        //after Java 7
        log.info("------------------Using Files.BufferedReader ------------------------");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("chapter11/read-write-file/src/main/resources/input/vultures.txt"), StandardCharsets.UTF_8))){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if(!line.contains("Ooh")) {
                    sb.append(line).append("\n");
                }
            }
            log.info("Read with BufferedReader --> {}", sb.toString() );
        } catch (Exception e) {
            log.info("Something went wrong! ", e);
        }

        Path sourceFile = Paths.get(file.toURI());
        try (BufferedReader reader = Files.newBufferedReader(sourceFile, StandardCharsets.UTF_8)){
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if(!line.contains("Ooh")) {
                    sb.append(line).append("\n");
                }
            }
            log.info("Read with BufferedReader --> {}", sb.toString() );
        } catch (Exception e) {
            log.info("Something went wrong! ", e);
        }
    }

}
