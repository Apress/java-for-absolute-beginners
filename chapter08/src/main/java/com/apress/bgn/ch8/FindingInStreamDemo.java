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

import com.apress.bgn.ch8.util.Song;
import com.apress.bgn.ch8.util.SongTransformer;
import com.apress.bgn.ch8.util.StreamMediaLoader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class FindingInStreamDemo {
    public static void main(String... args) {
        System.out.println("----- findAny, anyMatch, allMatch, noneMatch -----");
        Stream<Song> songs = StreamMediaLoader.loadSongs();
        Optional<Song> optSong = songs.parallel().findAny();
        optSong.ifPresent(System.out::println);

        songs = StreamMediaLoader.loadSongs();
        boolean b = songs.anyMatch(s -> s.getTitle().contains("Paper"));
        System.out.println("Are there songs with title containing \"Paper\"? " +  b);

        songs = StreamMediaLoader.loadSongs();
        b = songs.allMatch(s -> s.getDuration() > 300);
        System.out.println("Are all songs longer than 5 minutes? " +  b);

        songs = StreamMediaLoader.loadSongs();
        b = songs.noneMatch(s -> s.getDuration() > 300);
        System.out.println("Are all songs shorter than 5 minutes? " +  b);

        System.out.println("----- skip -----");
        songs = StreamMediaLoader.loadSongs();
        b = songs.skip(6).anyMatch(s -> s.getTitle().contains("Paper"));
        System.out.println("Are there songs with title containing \"Paper\"? " +  b);

        System.out.println("----- peek, skip -----");
        songs = StreamMediaLoader.loadSongs();
        List<String> result = songs.filter(s -> s.getDuration() > 300)
           .peek(e -> System.out.println("\t Filtered value: " + e))
           .map(Song::getTitle)
           .peek(e -> System.out.println("\t Mapped value: " + e))
           .collect(Collectors.toList());
        result.forEach(System.out::println);

    }
}
