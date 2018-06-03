package com.apress.bgn.ch5;

public class NumericDemo {
    private byte b;
    private short s;
    private int i;
    private long l;
    private float f;
    private double d;

    public static void main(String... args) {
        NumericDemo nd = new NumericDemo();

        // testing equality of zeroes
        System.out.println("Int zero: " + nd.i);
        System.out.println("Float zero: " + nd.f);
        if (nd.i == nd.f) {
            System.out.println("Both are zero.");
        }

        System.out.println(nd);

        //initializations
        nd.b = 0b1100;
        System.out.println("Byte binary value: " + nd.b);

        nd.i = 42 ;     // decimal case
        nd.i = 045 ;    // octal case - base 8
        System.out.println("Int octal value: " + nd.i);
        nd.i = 0xcafe ; // hexadecimal case - base 16
        System.out.println("Int hexadecimal value: " + nd.i);

        nd.i = 0b10101010101010101010101010101011;
        System.out.println("Int binary value: " + nd.i);

        //Java 7 syntax
        nd.i = 0b1010_1010_1010_1010_1010_1010_1010_1011;
        System.out.println("Int binary value: " + nd.i);

        nd.l = 1000_000l; // equivalent to 1000_000L
        System.out.println("Long value: " + nd.l);

        nd.f =  5;
        System.out.println("Integer value assigned to a float variable: " + nd.f);

        nd.f =  2.5f; // equivalent to nd.f =  2.5F;
        System.out.println("Decimal value assigned to a float variable: " + nd.f);

        nd.d =  2.5d; // equivalent to  nd.d =  2.5D;
        System.out.println("Decimal value assigned to a double variable: " + nd.f);

        // implicit conversions
        short sb = nd.b;
        System.out.println("byte to short: " + sb);

        int ib = nd.b;
        System.out.println("byte to int: " + ib);

        long lb = nd.b;
        System.out.println("byte to long: " + lb);

        byte bv = 23;
        short sbv = bv;
        System.out.println("byte to short: " + sbv);

        int ibv = bv;
        System.out.println("byte to int: " + ibv);

        // explicit conversion
        ibv = 130;
        bv = (byte) ibv;
        System.out.println("int to byte: " + bv);

        // comparison and assignements between different numeric types
        nd.f = nd.l;
        nd.i = 2;
        if (nd.i < nd.d) {
            System.out.println("Int smaller than double value!");
        }


        float maxLongF = Long.MAX_VALUE;
        System.out.println("max long= " + Long.MIN_VALUE);
        System.out.println("float max long= " + maxLongF);

        String s = String.format("%-24s: min = %s, max = %s", Float.class, Float.MIN_VALUE, Float.MAX_VALUE);
        System.out.println(s);

        s = String.format("%-24s: min = %s, max = %s", Double.class, Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.println(s);
    }

    @Override
    public String toString() {
        return "NumericDemo{" +
                "b=" + b +
                ", s=" + s +
                ", i=" + i +
                ", l=" + l +
                ", f=" + f +
                ", d=" + d +
                '}';
    }
}
