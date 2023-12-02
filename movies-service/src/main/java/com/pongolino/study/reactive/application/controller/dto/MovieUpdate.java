package com.pongolino.study.reactive.application.controller.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieUpdate {
    private final String name;
    private final Integer year;
    private final List<String> cast;
    private final LocalDate release_date;

    public MovieUpdate(String name, Integer year, List<String> cast, LocalDate release_date) {
        this.name = name;
        this.year = year;
        this.cast = cast;
        this.release_date = release_date;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public List<String> getCast() {
        return cast;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }
}
