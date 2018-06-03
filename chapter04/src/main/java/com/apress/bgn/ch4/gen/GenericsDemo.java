package com.apress.bgn.ch4.gen;

import com.apress.bgn.ch4.hierarchy.Gender;
import com.apress.bgn.ch4.hierarchy.Performer;

public class GenericsDemo {
    public static void main(String... args) {
        Performer john = new Performer("John", 40, 1.91f, Gender.MALE);
        Performer jane = new Performer("Jane", 34, 1.591f, Gender.FEMALE);

        Pair<Performer, Performer>  performerPair = Pair.of(john, jane);
        System.out.println(performerPair);

        Pair<String, String> stringPair = Pair.of("John", "Jane");
        System.out.println(stringPair);

        Pair<String, Performer> spPair = Pair.of("John", john);
        System.out.println(spPair);

        System.out.println("all good.");
    }
}
