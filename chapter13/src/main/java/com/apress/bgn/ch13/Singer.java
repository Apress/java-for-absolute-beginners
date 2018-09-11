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
package com.apress.bgn.ch13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class Singer implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Singer.class);
    private static final long serialVersionUID = 42L;
    private final long birthtime;

    private String name;

    private Double rating;

    private LocalDate birthDate;

    public Singer(String name, Double rating, LocalDate birthDate) {
        this.name = name;
        this.rating = rating;
        this.birthDate = birthDate;
        this.birthtime = System.nanoTime();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Singer singer = (Singer) o;
        return Objects.equals(name, singer.name) &&
                Objects.equals(rating, singer.rating) &&
                Objects.equals(birthDate, singer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, birthDate);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            long deathtime = System.nanoTime();
            long lifespan = (deathtime - birthtime) / 1_000_000_000;
            log.info("GC Destroyed: {} after {} seconds", name, lifespan);

        } finally {
            super.finalize();
        }
    }
}

