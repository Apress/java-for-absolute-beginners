/*
Freeware License, some rights reserved

Copyright (c) 2018 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ch8;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class BasicStreamsDemo {
    public static void main(String... args) throws Exception {
        List<Integer> bigList = List.of(50, 10, 250, 100, 23, 45, 33, 55, 67, 83, 90, 92, 94, 74, 200, 40052, 3467, 125);


        bigList.stream()
                //.filter(i -> i % 3 == 0)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + ": " + i));

        bigList.parallelStream()
                //.filter(i -> i % 3 == 0)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + ": " + i));

        System.out.println("----- builder() -----");
        Stream<Integer> built = Stream.<Integer>builder().add(50).add(10).add(250).build();
        built.forEach(System.out::println);

        System.out.println("----- generated() -----");
        Stream<Integer> generated = Stream.generate(
                () -> new Random().nextInt(300) + 1
        ).limit(5);
        generated.forEach(System.out::println);

        System.out.println("----- iterate() -----");
        Stream<Integer> iterated = Stream.iterate(0, i -> i < 50, i -> i + 5);
        //Stream<Integer> iterated = Stream.iterate(0, i -> i + 5).limit(15);
        iterated.forEach(System.out::println);

        System.out.println("----- IntStream -----");
        IntStream intStream = IntStream.range(0, 10);
        intStream.forEach(System.out::println);

        System.out.println("----- Builder -----");
        intStream = IntStream.builder().add(0).add(1).add(2).add(5).build();
        intStream.forEach(System.out::println);

        System.out.println("----- of -----");
        intStream = IntStream.of(0, 1, 2, 3, 4, 5);
        intStream.forEach(System.out::println);

        System.out.println("----- random.ints -----");
        Random random = new Random();
        intStream = random.ints(5);
        intStream.forEach(System.out::println);

        System.out.println("----- rangeClosed -----");
        intStream = IntStream.range(0, 10);
        intStream.forEach(System.out::println);

        System.out.println("----- charStream -----");
        intStream = IntStream.of('a', 'b', 'c', 'd');
        intStream.forEach(c -> System.out.println((char) c));

        System.out.println("----- charStream of String -----");
        IntStream charStream = "sample".chars();
        charStream.forEach(c -> System.out.println((char) c));

        System.out.println("----- LongStream -----");
        LongStream longStream = LongStream.range(0, 10);
        longStream.forEach(System.out::println);


        System.out.println("----- DoubleStream.of -----");
        DoubleStream doubleStream = DoubleStream.of(1, 2, 2.3, 3.4, 4.5, 6);
        doubleStream.forEach(System.out::println);

        System.out.println("----- DoubleStream.doubles -----");
        doubleStream = random.doubles(3);
        doubleStream.forEach(System.out::println);

        System.out.println("----- DoubleStream.iterate -----");
        doubleStream = DoubleStream.iterate(2.5, d -> d = d + 0.2).limit(10);
        doubleStream.forEach(System.out::println);

        System.out.println("----- stringStream -----");
        Stream<String> stringStream = Pattern.compile(" ").splitAsStream("live your life");
        stringStream.forEach(System.out::println);


        //decomment this when Gradle works with Java 11
       /* System.out.println("----- stringStream from File -----");
        String inputPath = "chapter08/src/main/resources/songs.csv";
        stringStream = Files.lines(Path.of(inputPath));
        stringStream.forEach(System.out::println);

        System.out.println("----- stringStream from File -----");
        stringStream = Files.lines(Path.of(inputPath), Charset.forName("UTF-8"));
        stringStream.forEach(System.out::println);*/
    }
}
