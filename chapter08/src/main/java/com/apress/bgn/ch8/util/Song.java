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

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

import java.util.Objects;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
@CsvDataType
public class Song implements Comparable<Song> {
    /**
     * unique identifier in the media library
     */
    @CsvField(pos = 1)
    private Long id;

    @CsvField(pos = 2)
    private String singer;
    /**
     * name of a song
     */
    @CsvField(pos = 3)
    private String title;
    /**
     * Duration of a song in seconds
     */
    @CsvField(pos = 4)
    private Integer duration;

    @CsvField(pos = 5)
    private AudioType audioType;

    public Long getId() {
        return id;
    }

    public String getSinger() {
        return singer;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

    public AudioType getAudioType() {
        return audioType;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", singer='" + singer + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", audioType=" + audioType +
                "}";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setAudioType(AudioType audioType) {
        this.audioType = audioType;
    }

    @Override
    public int compareTo(Song o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) &&
                Objects.equals(singer, song.singer) &&
                Objects.equals(title, song.title) &&
                Objects.equals(duration, song.duration) &&
                audioType == song.audioType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, singer, title, duration, audioType);
    }
}
