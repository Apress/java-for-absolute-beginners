package com.apress.bgn.ch4.hierarchy;


/**
 * @author iuliana.cosmina
 * @date 11/04/2018
 * @since 1.0
 */
public class Sample extends Object {
    public static void main(String... args) {
        System.out.println(Gender.FEMALE.comment());
        System.out.println(Gender.MALE.comment());
        System.out.println(Gender.UNDEFINED.comment());

    }
}

