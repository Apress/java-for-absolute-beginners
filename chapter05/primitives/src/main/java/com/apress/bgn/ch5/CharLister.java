package com.apress.bgn.ch5;

public class CharLister {
    public static void main(String... args) {
        for (int i = 0; i < 65536; ++i) {
            char c = (char) i;
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print("c[" + i + "]=" + c + " ");
        }
    }
}
