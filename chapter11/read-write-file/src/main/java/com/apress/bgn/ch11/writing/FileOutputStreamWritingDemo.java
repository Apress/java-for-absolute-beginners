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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class FileOutputStreamWritingDemo {
    private static final Logger log = LoggerFactory.getLogger(FileOutputStreamWritingDemo.class);

    public static void main(String... args) {
        File file = new File("chapter11/read-write-file/src/main/resources/output/vultures3.txt");

        List<String> lyricList =  List.of("Some of us, we're hardly ever here",
                "The rest of us, we're born to disappear",
                "How do I stop myself from",
                "Being just a number?");

        try (FileOutputStream output = new FileOutputStream(file)){
            lyricList.forEach(lyric -> {
                try {
                    output.write(lyric.getBytes());
                    output.write("\n".getBytes());
                } catch (IOException e) {
                    log.info("Something went wrong! ", e);
                }
            });
            output.flush();
        } catch (FileNotFoundException e) {
            log.info("Something went wrong! ", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
