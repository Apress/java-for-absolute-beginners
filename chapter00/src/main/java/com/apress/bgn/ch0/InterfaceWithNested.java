package com.apress.bgn.ch0;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public interface InterfaceWithNested {

    class NotStaticNested {
        NotStaticNested() {
            System.out.println("I am non-static nested!");
        }
    }
}
