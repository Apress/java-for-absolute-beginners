package com.apress.bgn.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * Class <code>ProcessListingDemo</code> is an entry point of this application, used to test The Java Process API.<p>
 *
 * @author Iuliana Cosmina
 * since 1.0
 */
public class ProcessListingDemo {

    private static final Logger log = LoggerFactory.getLogger(ProcessListingDemo.class);

    public static void main(String... args) {
        Optional<String> currUser = ProcessHandle.current().info().user();

        ProcessHandle.allProcesses()
                .filter(ph -> ph.info().user().equals(currUser)
                        && ph.info().commandLine().get().contains("java")).forEach(p -> {
            log.info("PID: " + p.pid());
            p.info().arguments().ifPresent(s -> Arrays.stream(s).forEach(a -> log.info("   {}", a)));


            p.info().command().ifPresent(c -> log.info("\t Command: {}", c));
        });
    }

}