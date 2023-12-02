package com.pongolino.study.reactive.application.controller.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieCreationResponse {
    private String name;
    private Integer year;
    private List<String> cast;
    private LocalDate release_date;

    public MovieCreationResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }
}
