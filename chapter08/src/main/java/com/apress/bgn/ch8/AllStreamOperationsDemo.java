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

import com.apress.bgn.ch8.util.AudioType;
import com.apress.bgn.ch8.util.Song;
import com.apress.bgn.ch8.util.SongTransformer;
import com.apress.bgn.ch8.util.StreamMediaLoader;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class AllStreamOperationsDemo {
    public static void main(String... args) {
        Stream<Song> songs = StreamMediaLoader.loadSongs();
        System.out.println("----- forEach -----");
        songs.forEach(System.out::println);

        System.out.println("----- forEach + CAPS -----");
        songs = StreamMediaLoader.loadSongs();
        songs.forEach(song -> {
            song.setSinger(song.getSinger().toUpperCase());
            System.out.println(song);
        });

        System.out.println("----- forEachOrdered -----");
        songs = StreamMediaLoader.loadSongs();
        songs.parallel().forEachOrdered(System.out::println);

        System.out.println("----- filter -----");
        songs = StreamMediaLoader.loadSongs();
        Song[] sarray = songs.filter(s -> s.getAudioType() == AudioType.MP3).toArray(Song[]::new);
        Arrays.stream(sarray).forEach(System.out::println);

        System.out.println("----- map -----");
        songs = StreamMediaLoader.loadSongs();
        Function<Song, Integer> fct = SongTransformer::processDuration;
        List<Integer> durationAsMinutes = songs.map(fct).collect(Collectors.toList());
        durationAsMinutes.forEach(System.out::println);

        System.out.println("----- flatMap -----");
        List<List<Integer>> testList = List.of(List.of(2, 3), List.of(4, 5), List.of(6, 7));
        System.out.println(processList(testList));

        System.out.println("----- sorted -----");
        List<String> pieces = List.of("some", "of", "us", "we're", "hardly", "ever", "here");
        String first = pieces.stream().sorted().findFirst().get();
        System.out.println("First from sorted list: " + first);

        System.out.println("----- distinct -----");
        pieces = List.of("as", "long", "as", "there", "is", "you", "there", "is", "me");
        long count = pieces.stream().distinct().count();
        System.out.println("Elements in the stream: " + count);

        System.out.println("----- limit, min, max -----");
        Stream<Integer> ints = Stream.of(5, 2, 7, 9, 8, 1, 12, 7, 2);
        ints.limit(4).min(Integer::compareTo).ifPresent(min -> System.out.println("Min is: " + min));

        ints = Stream.of(5, 2, 7, 9, 8, 1, 12, 7, 2);
        ints.limit(4).max(Integer::compareTo).ifPresent(max -> System.out.println("Max is: " + max));

        System.out.println("----- reduce, sum -----");
        songs = StreamMediaLoader.loadSongs();
        int totalDuration = songs
                .mapToInt(Song::getDuration)
                .sum();
        System.out.println("Total duration without reduce:" + totalDuration);

        songs = StreamMediaLoader.loadSongs();
        totalDuration = songs
                .mapToInt(Song::getDuration)
                .reduce(
                        0, (a, b) -> a + b);
        System.out.println("Total duration with reduce:" + totalDuration);

        System.out.println("----- takeWhile  ordered -----");
        Stream<Integer> forTaking = Stream.of( 3, 6, 9, 11, 12, 13, 15);
        forTaking.takeWhile(s -> s % 3 == 0 ).forEach(s -> System.out.print(s + " "));

        System.out.println("\n----- takeWhile  non-ordered -----");
        forTaking = Stream.of( 3, 6, 9, 2, 4, 8, 12, 36, 18, 42, 11, 13, 15);
        forTaking.takeWhile(s -> s % 3 == 0 ).forEach(s -> System.out.print(s + " "));

        System.out.println("\n----- dropWhile  ordered -----");
        Stream<Integer> forDropping = Stream.of( 3, 6, 9, 11, 12, 13, 15);
        forDropping.dropWhile(s -> s % 3 == 0 ).forEach(s -> System.out.print(s + " "));

        System.out.println("\n----- dropWhile  non-ordered -----");
        forDropping = Stream.of( 3, 2, 9, 6, 4, 8, 12, 36, 18, 42, 11, 13, 15);
        forDropping.dropWhile(s -> s % 3 == 0 ).forEach(s -> System.out.print(s + " "));
    }

    public static List<Integer> processList(List<List<Integer>> list) {
        List<Integer> result = list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return result;
    }
}
