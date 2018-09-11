package com.apress.bgn.ch5;

import com.apress.bgn.ch4.hierarchy.Gender;
import com.apress.bgn.ch4.hierarchy.Performer;

import java.util.*;

public class CollectionsDemo {

    private static void badList() {
        //before Java 1.5
        List objList = new ArrayList();
        objList.add("temp");
        objList.add(Integer.valueOf(5));
        objList.add(new Performer("John", 40, 1.91f, Gender.MALE));

        for (Object obj : objList) {
            if (obj instanceof String) {
                System.out.println("String object = " + obj.toString());
            } else if (obj instanceof Integer) {
                Integer i = (Integer) obj;
                System.out.println("Integer object = " + obj.toString());
            } else {
                Performer p = (Performer) obj;
                System.out.println("Performer object = " + p.getName());
            }
        }
    }

    private static void goodList() {
        //after Java 1.5 , diamond operator in Java 1.8
        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");

        for (String s : stringList) {
            System.out.println(s);
        }
    }

    private static void lambdaList() {
        //factory methods since 9
        var stringList = List.of("one", "two", "three");

        //lambda Java 1.8
        //stringList.forEach(element -> System.out.println(element));
        //or using a method reference
        stringList.forEach(System.out::println);
    }

    private static void goodSet() {
        Set<String> StringSet = new HashSet<>();
        StringSet.add("one");
        StringSet.add("two");
        StringSet.add("three");

        for (String s : StringSet) {
            System.out.println(s);
        }
    }

    private static void goodMap() {
        var stringMap = new HashMap<String, Integer>();
        stringMap.put(null, 0);
        stringMap.put("one", 1);
        stringMap.put("two", 2);
        stringMap.put("three", 3);

        for (Map.Entry<String, Integer> entry : stringMap.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }
    }


    private static void lambdaMap() {
        Map<String, Integer> stringMap = Map.of("one", 1, "two", 2, "three", 3);

        //stringMap.entrySet().forEach(e -> System.out.println(e.getKey() + ":  " + e.getValue()));
        stringMap.forEach((k, v) -> System.out.println(k + ":  " + v));
    }

    public static void main(String... args) {
        badList();
        System.out.println("------------");
        goodList();
        System.out.println("------------");
        goodSet();
        System.out.println("------------");
        lambdaList();
        System.out.println("------------");
        goodMap();
    }
}
