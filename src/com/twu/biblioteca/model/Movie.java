package com.twu.biblioteca.model;

import com.twu.biblioteca.LibraryItem;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class Movie extends LibraryItem {
    private int year;
    private String director;
    private int rate;

    public Movie(String movie, int year, String director, int rate) {
        super(movie);
        this.year = year;
        this.director = director;
        this.rate = rate;
    }


    public int getMovieYear() {
        return this.year;
    }

    public String getMovieDirector() {
        return this.director;
    }

    public int getMovieRate() {
        return this.rate;
    }

    @Override
    public String toString(){
        return this.name + " " + this.year + " " + this.director + " " + this.rate;
    }
}
