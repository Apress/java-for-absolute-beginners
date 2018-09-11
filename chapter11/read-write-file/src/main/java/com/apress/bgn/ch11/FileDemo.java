package com.apress.bgn.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

public class FileDemo {
    private static final Logger log = LoggerFactory.getLogger(FileDemo.class);

    public static void main(String... args) {
        File f = new File("/Users/iulianacosmina/apress/workspace/java-bgn/chapter11/read-write-file/src/main/resources/input/vultures.txt");
        printFileStats(f);
        log.info("------------------------------------------");

        f = new File("chapter11/read-write-file/src/main/resources/input/vultures.txt");
        printFileStats(f);
        log.info("------------------------------------------");

        try {
            URI uri = new URI("file:///Users/iulianacosmina/apress/workspace/java-bgn/chapter11/read-write-file/src/main/resources/input/vultures.txt");
            f = new File(uri);
            printFileStats(f);
        }catch (URISyntaxException use) {
            log.error("Malformed URL, no file there", use);
        }

        log.info("------------------------------------------");

        File d = new File("chapter11/read-write-file/src/main/resources/input/");
        printFileStats(d);
        log.info("------------------------------------------");

        File h = new File("/Users/iulianacosmina/.bash_profile");
        printFileStats(h);
        log.info("------------------------------------------");

        Arrays.stream(d.listFiles()).forEach(ff -> log.info("\t File Name : {}", ff.getName()));
        log.info("------------------------------------------");

        Arrays.stream(d.list()).forEach(ff -> log.info("\t File Name : {}", ff));
        log.info("------------------------------------------");

        Arrays.stream(Objects.requireNonNull(d.listFiles(pathname -> pathname.getAbsolutePath().endsWith("yml") || pathname.getAbsolutePath().endsWith("properties")))).forEach(ff -> log.info("\t YML/Properties file : {}", ff.getName()));
        log.info("------------------------------------------");

        Arrays.stream(d.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("son");
            }
        })).forEach(ff -> log.info("\t Namesakes : {}", ff.getName()));
        log.info("------------------------------------------");

        File file =  new File("chapter11/read-write-file/src/main/resources/output/sample/created.txt");
        File renamed =  new File("chapter11/read-write-file/src/main/resources/output/sample/created.txt");
        boolean result = file.renameTo(renamed);
        log.info("Renaming succeeded? : {} ", result);
        log.info("------------------------------------------");

        File created = new File("chapter11/read-write-file/src/main/resources/output/sample/created.txt");
        if (!created.exists()) {
            try {
                created.createNewFile();
            } catch (IOException e) {
                log.error("Could not create file.", e);
            }
        }

        if (created.exists()) {
            created.delete();
            log.info("Deleted file " + created.getName());
        }
        log.info("------------------------------------------");

        try {
            File temp = File.createTempFile("java_bgn_", ".tmp");
            log.info("File created.txt at: {}", temp.getAbsolutePath());
            temp.deleteOnExit();
        } catch (IOException e) {
            log.error("Could not create temporary file.", e);
        }
    }

    private static void printFileStats(File f) {
        if (f.exists()) {
            log.info("File Details:");
            log.info("Type : {}", f.isFile() ? "file" : "directory or symlink");
            log.info("Location :{}", f.getAbsolutePath());
            log.info("Parent :{}", f.getParent());
            log.info("Name : {}", f.getName());

            double kilobytes = f.length() / (double)1024;
            log.info("Size : {} ", kilobytes);

            log.info("Is Hidden : {}", f.isHidden());
            log.info("Is Readable? : {}", f.canRead());
            log.info("Is Writable? : {}", f.canWrite());
        }
    }

}
