package com.apress.bgn.ch4.hierarchy;

import java.util.List;

public class Performer extends Human implements Musician, Actor {

    private String genre;

    private List<String> songs;

    private String school;

    private List<String> films;

    public Performer(String name, int age, float height, Gender gender) {
        super(name, age, height, gender);
    }

    @Override
    public int getTimeToLive() {
        return (LIFESPAN - getAge()) / 2;
    }


    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public void addSong(String song) {
        this.songs.add(song);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public void addFilm(String filmName) {
        this.films.add(filmName);
    }
}
