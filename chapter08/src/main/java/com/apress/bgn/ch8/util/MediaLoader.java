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
package com.apress.bgn.ch8.util;

import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class MediaLoader {

    public static List<Song> loadSongs() {
        List<Song> songs = new ArrayList<>();
        Deserializer deserializer = CsvIOFactory.createFactory(Song.class).createDeserializer();
        String inputFile = "chapter08/src/main/resources/songs.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            deserializer.open(reader);
            while (deserializer.hasNext()) {
                Song s = deserializer.next();
                songs.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            deserializer.close(true);
        }
        return songs;
    }
}
