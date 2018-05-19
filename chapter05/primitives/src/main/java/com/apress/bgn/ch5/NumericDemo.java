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
        System.out.println(nd);

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

        ibv = 130;
        bv = (byte) ibv;
        System.out.println("int to byte: " + bv);

        nd.f = nd.l;
        nd.i = 2;
        if (nd.i < nd.d) {
            System.out.println("yeey!");
        }



        float maxLongF = Float.MIN_VALUE;
        System.out.println("max long= " + Float.MIN_VALUE);
        System.out.println("float max long= " + maxLongF);

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
