package com.apress.bgn.ch4.basic;

/**
 * @author iuliana.cosmina
 * @date 11/04/2018
 * @since 1.0
 */
public class Vehicle {
    int numberOfWheels;

    int speed;

    String color;

    public void accelerate(){
        speed = speed + 1;
    }


    public void brake(){
        speed = speed - 1;
    }
}
