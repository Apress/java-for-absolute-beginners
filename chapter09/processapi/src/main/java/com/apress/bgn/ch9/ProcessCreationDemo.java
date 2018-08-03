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
package com.apress.bgn.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class ProcessCreationDemo {
    private static final Logger log = LoggerFactory.getLogger(ProcessCreationDemo.class);

    public static void main(String... args) {
        try {

            Process exec = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "echo Java home:  $JAVA_HOME" });
            exec.waitFor();
            InputStream is = exec.getInputStream();
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            log.info("Process output -> {}", textBuilder.toString());
            log.info("process result: {}", exec.exitValue());

            /*ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "echo Java home:  $JAVA_HOME").inheritIO();
            Process p = pb.start();
            CompletableFuture<Process> future = p.onExit();
            int result = future.get().exitValue();
            log.info("process result: " + result);*/

            List<ProcessBuilder> builders = List.of(
                    new ProcessBuilder("/bin/sh", "-c", "echo \"start...\" ; sleep 3; echo \"done.\"").inheritIO(),
                    new ProcessBuilder("/bin/sh", "-c", "echo \"start...\" ; sleep 3; echo \"done.\"").inheritIO(),
                    new ProcessBuilder("/bin/sh", "-c", "echo \"start...\" ; sleep 3; echo \"done.\"").inheritIO()

            );
            builders.parallelStream().forEach(pbs -> {
                try {
                    pbs.start();
                } catch (Exception e) {
                    log.error("Oops, could not start process!", e);
                }
            });

            ProcessHandle ph = ProcessHandle.current();
            ph.children().forEach(pc -> {
                log.info("Child PID: {}", pc.pid());
                pc.parent().ifPresent(parent -> log.info("  Parent PID: {}", parent.pid()));

            });
            System.out.println("Press any key to exit!");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
