package com.apress.bgn.ch5;

import java.util.Date;

/**
 * Class used to play a little with primitive values.
 * Just remove numbers from main methods to run them
 */
public class PrimitivesDemo {

    public static void main1(String... args) {
        int k = 42;
        int q = k;

        System.out.println("k = " + k);
        System.out.println("q = " + q);
    }


    public static void main2(String... args) {
        int i = 5;
        int j = 7;
        Date d = new Date();
        int result = add(i, j);
        System.out.println(result);
        d= null;
    }

    static int add(int a, int b) {
        String mess = new String("performing add ...");
        return a + b;
    }

    public static void main3(String... args) {
        int k = 42;
        int q = 44;

        int temp = k;
        k = q;
        q = temp;

        System.out.println("k = " + k);
        System.out.println("q = " + q);
    }

    public static void main4(String... args) {
        int k = 42;
        int q = 44;

        swap(k, q);

        System.out.println("k = " + k);
        System.out.println("q = " + q);
    }

    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

}
