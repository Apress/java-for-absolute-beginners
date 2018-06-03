package com.apress.bgn.ch4.hierarchy;

import java.util.List;

/**
 * @author iuliana.cosmina
 * @date 11/04/2018
 * @since 1.0
 */
public interface Actor extends Artist {

    List<String> getFilms();

    void setFilms(List<String> films);

    void addFilm(String filmName);
}
