package com.example.myfirstlabapplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    @JsonProperty("Search")
    Film[] films;

    public void setFilms(Film[] films) {
        this.films = films;
    }

    public Film[] getFilms() {
        return films;
    }

    Search(){}
}
