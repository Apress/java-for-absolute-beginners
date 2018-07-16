package com.apress.bgn.ch8;

import com.apress.bgn.ch8.util.AudioType;
import com.apress.bgn.ch8.util.Song;

import static com.apress.bgn.ch8.util.MediaLoader.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by iuliana.cosmina on 3/6/18
 */
public class IntroductoryMain {

    public static void main(String... args) {
        List<Song> songList = loadSongs();
        List<Song> resultedSongs = new ArrayList<>();

        //find all songs with duration of at least 300 seconds
        for (Song song: songList) {
            if (song.getDuration() >= 300) {
                resultedSongs.add(song);
            }
        }
        // save the names in a list and sort them in decreasing order of their duration.
        Collections.sort(resultedSongs, (s1, s2) -> s2.getDuration().compareTo(s1.getDuration()));
        System.out.println(resultedSongs);
        List<String> finalList0 = new ArrayList<>();
        for (Song song: resultedSongs) {
            finalList0.add(song.getTitle());
        }
        System.out.println("Before Java 8: " + finalList0);

        // doing the same using a stream
        List<String> finalList = songList.stream().filter(s -> s.getDuration() >= 300)
                .sorted(Comparator.comparing(Song::getDuration).reversed())
                .map(Song::getTitle)
                .collect(Collectors.toList());
        System.out.println(" After Java 8: " + finalList);

    }


}
