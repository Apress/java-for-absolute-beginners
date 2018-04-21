package com.apress.bgn.ch4.hierarchy;

/**
 * @author iuliana.cosmina
 * @date 09/04/2018
 * @since 1.0
 */
public class Bicycle implements Vehicle {
    private int wheels;
    private int speed;

    private int type; //race, track, BMX, mountain

    @Override
    public void brake() {

    }

    @Override
    public void accelerate() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }
}
