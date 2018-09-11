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
package com.apress.bgn.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class PathDemo {
    private static final Logger log = LoggerFactory.getLogger(PathDemo.class);

    public static void main(String... args) {
        File file = new File("chapter11/read-write-file/src/main/resources/input/vultures.txt");
        Path path = Paths.get(file.toURI());
        //Path path = Path.of(file.toURI());
        log.info(path.toString());

        Path composedPath = Paths.get("/Users/iulianacosmina/apress/workspace","java-bgn/chapter11/read-write-file/src/main/resources/input","vultures.txt");
        log.info(composedPath.toString());

        log.info("Is the same path? : {} ", path.compareTo(composedPath) ==0 ? "yes" : "no");

        try {
            log.info("Is same file? : {} ", Files.isSameFile(path,composedPath));
        } catch (IOException e) {
            log.error("Something unexpected happened!", e);
        }

        log.info("Location :{}", path.toAbsolutePath());
        log.info("Is Absolute? : {}", path.isAbsolute());
        log.info("Parent :{}", path.getParent());
        log.info("Root : {}", path.getRoot());
        log.info("FileName : {}", path.getFileName());
        log.info("FileSystem : {}", path.getFileSystem());

        Path chapterPath = Paths.get("/Users/iulianacosmina/apress/workspace","java-bgn/chapter11");
        Path filePath = chapterPath.resolve("read-write-file/src/main/resources/input/vultures.txt");
        log.info("Resolved Path :{}", filePath.toAbsolutePath());

        Iterator<Path> iterator = path.iterator();
        iterator.forEachRemaining(p -> log.info("Part of path : {} ", p));

        log.info("----------- Files play------------");

        Path outputPath = FileSystems.getDefault().getPath("/Users/iulianacosmina/apress/workspace/" +
                "java-bgn/chapter11/read-write-file/src/main/resources/output/sample2");
        try {
            Files.createDirectory(outputPath);
            log.info("Type: {}", Files.isDirectory(outputPath) ? "yes" : "no");

            Path destPath = Paths.get(outputPath.toAbsolutePath().toString(), "vultures.txt");
            Files.copy(path, destPath);
            double kilobytes = Files.size(destPath) / (double)1024;
            log.info("Size : {} ", kilobytes);

            Path newFilePath = Paths.get(outputPath.toAbsolutePath().toString(), "vultures2.txt");
            Files.createFile(newFilePath);
            log.info("Type: {}", Files.isRegularFile(newFilePath) ? "yes" : "no");
            log.info("Type: {}", Files.isSymbolicLink(newFilePath) ? "yes" : "no");

            log.info("Is Hidden: {}", Files.isHidden(newFilePath) ? "yes" : "no");
            log.info("Is Readable: {}", Files.isReadable(newFilePath) ? "yes" : "no");
            log.info("Is Writable: {}", Files.isWritable(newFilePath) ? "yes" : "no");

            Path copyFilePath = Paths.get("/Users/iulianacosmina/temp/", "vultures3.txt");
            Files.move(newFilePath, copyFilePath);
            log.info("Exists? : {}", Files.exists(copyFilePath)? "yes": "no");
            log.info("File moved to: {}", copyFilePath.toAbsolutePath());

            Files.deleteIfExists(copyFilePath);
        } catch (FileAlreadyExistsException e) {
            log.error("Creation failed!", e);
        } catch (IOException e) {
            log.error("Something unexpected happened!", e);
        }


    }
}
