package com.apress.bgn.ch4.basic;

/**
 * @author iuliana.cosmina
 * @date 21/04/2018
 * @since 1.0
 */
public class BasicHumanDemo {

    public static void main(String... args) {
        Human john = new Human();
        john.name = "John";


        Human jane = new Human();
        jane.name = "Jane";

        System.out.println("John's lifespan = " + john.LIFESPAN);
        System.out.println("Jane's lifespan = " + jane.LIFESPAN);


        System.out.println("Human lifespan = " + Human.LIFESPAN);
    }

}
