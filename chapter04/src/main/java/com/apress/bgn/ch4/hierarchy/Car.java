package com.apress.bgn.ch4.hierarchy;

/**
 * @author iuliana.cosmina
 * @date 09/04/2018
 * @since 1.0
 */
public class Car implements Vehicle {
    private int wheels;
    private int speed;

    private String vinNumber;
    private float engineCapacity;
    private int seatCapacity;
    private int fuelcapacity;

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
