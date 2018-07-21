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

import com.apress.bgn.ch8.util.MediaLoader;
import com.apress.bgn.ch8.util.Song;
import com.apress.bgn.ch8.util.SongTransformer;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class OptionalDemo {
    public static void main(String... args) {
        //declaring empty Optional
        Optional<Song> empty = Optional.empty();
        //Optinal containing a long value
        Optional<Long> value = Optional.of(5L);

        Song song = null;
        Optional<Song> nullable = Optional.ofNullable(song);
        //System.out.println("Nullable: " + nullable.get());

        // old-style find in list
        List<Song> songs = MediaLoader.loadSongs();
        song = findFirst(songs, "B.B. King");
        if(song != null && song.getSinger().equals("The Thrill Is Gone")) {
            System.out.println("Good stuff!");
        } else {
            System.out.println("not found!");
        }

        Optional<Song> opt = songs.stream().filter(ss -> "B.B. King".equals(ss.getSinger())).findFirst();
        opt.ifPresent(r -> System.out.println(r.getTitle()));
        //decomment this when gradle works with Java 11
        /*if(opt.isEmpty()) {
            System.out.println("Not found!");
        }*/
        System.out.println("---- ifPresentOrElse -----");
        opt.ifPresentOrElse(r -> System.out.println(r.getTitle()), () -> System.out.println("Not found!")) ;

        Optional<Song> opt2 = songs.stream().filter(ss -> "Rob Thomas".equals(ss.getSinger())).findFirst();
        System.out.println("Found Song " + opt2.get());
        System.out.println("More than 3 mins? " + isMoreThan3Mins(opt2.get()));

        Song defaultSong = new Song();
        defaultSong.setTitle("Untitled");

        Song s = opt.orElse(defaultSong);
        System.out.println("Found: " + s.getTitle());

        System.out.println("---- flatMap -----");
        Function<String, Optional<Integer>> toIntOpt = OptionalDemo::toIntOpt;

        Optional<String> str = Optional.of("42");
        Optional<Optional<Integer>> resInt = str.map(toIntOpt);

        // flatten it
        Optional<Integer> desiredRes = resInt.orElse(Optional.empty());
        System.out.println("finally: " + desiredRes.get());

        // no need to flaten it

        str = Optional.of("42");
        desiredRes = str.flatMap(toIntOpt);
        System.out.println("Flat result: "+ desiredRes.get());

        s = opt.orElseThrow(IllegalArgumentException::new);

    }

    public static Optional<Integer> toIntOpt(String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Song findFirst(List<Song> songs, String singer) {
        for (Song song: songs) {
            if (singer.equals(song.getSinger())) {
                return song;
            }
        }
        return null;
    }

    public static boolean isMoreThan3Mins(Song song) {
        return Optional.ofNullable(song)
                .map(SongTransformer::processDuration)
                .filter(d -> d >= 3)
                .filter(d -> d <= 10)
                .isPresent();
    }
}
