package com.apress.bgn.ch5.refs;

import com.apress.bgn.ch4.hierarchy.*;

public class ReferencesDemo {


    public static void main(String... args) {
        Performer performer = new Performer("John", 40, 1.91f, Gender.MALE);
        Human human = performer;
        Actor actor = performer;
        Musician musician = performer;

        //performer = musician;
        //or
        //performer = human;
        //or
        performer = (Performer) actor;
    }

    public static void main2(String... args) {
        IntContainer k = new IntContainer(42);
        IntContainer q = new IntContainer(44);

        swap2(k,q);

        System.out.println("k = " + k.getValue());
        System.out.println("q = " + q.getValue());

        Performer performer = new Performer("John", 40, 1.91f, Gender.MALE);
        Human human = new Performer("Jack", 40, 1.91f, Gender.MALE);
        Actor actor = new Performer("Jean", 40, 1.91f, Gender.MALE);
        Musician musician = new Performer("Jodie", 40, 1.71f, Gender.FEMALE);
    }

    static void swap(IntContainer a, IntContainer b) {
        IntContainer temp = a;
        a = b;
        b = temp;
    }

    static void swap2(IntContainer a, IntContainer b) {
        IntContainer temp = new IntContainer(a.getValue());
        a.setValue(b.getValue());
        b.setValue(temp.getValue());
    }
}
