package com.apress.bgn.ch7;

/**
 * Created by iuliana.cosmina on 3/6/18
 */
public class IfFlowDemo {

    public static void main(String... args) {
        //Read a
        int a = Integer.parseInt(args[0]);

        if (a < 0) {
            System.out.println("Negative");
        }

        if (a % 2 == 0) { // is even
            System.out.println("EVEN");
        } else {
            System.out.println("ODD");
        }
    }
}
