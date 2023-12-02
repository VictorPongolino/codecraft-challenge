package com.pongolino.study.reactive.application.controller.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieCreationRequest {
    private String name;
    private Integer year;
    private List<String> cast;
    private LocalDate release_date;

    public MovieCreationRequest() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public void setRelease_date(LocalDate release_date) {
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
