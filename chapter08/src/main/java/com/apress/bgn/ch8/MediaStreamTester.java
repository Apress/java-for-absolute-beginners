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
public class MediaStreamTester {
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
        List<String> pieces = List.of("some","of", "us", "we're", "hardly", "ever", "here");
        String first = pieces.stream().sorted().findFirst().get();
        System.out.println("First from sorted list: " + first);

        System.out.println("----- distinct -----");
        pieces = List.of("as","long", "as", "there", "is", "you", "there", "is", "me");
        long count = pieces.stream().distinct().count();
        System.out.println("Elements in the stream: " + count);
    }

    public static List<Integer> processList(List<List<Integer>> list) {
        List<Integer> result = list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return result;
    }
}
