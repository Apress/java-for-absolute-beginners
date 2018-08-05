package com.apress.bgn.ch10;

import java.io.IOException;

/**
 * Created by iuliana.cosmina on 3/6/18
 */
public class ReadingFormStdinDemo {

    public static void main(String... args) throws IOException {
        System.out.print("Press any key to terminate:");

        int read = System.in.read();
        System.out.println("Key pressed: " + read);
    }
}
