package com.twu.biblioteca;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class Movie {
    private String movieName;
    private int year;
    private String director;
    private int rate;
    private boolean checkOutStatus;

    public Movie(String name, int year, String director, int rate) {
        this.movieName = name;
        this.year = year;
        this.director = director;
        this.rate = rate;
        this.checkOutStatus = false;
    }

    public String getMovieName() {
        return this.movieName;
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

    public boolean getCheckOutStatus() {
        return this.checkOutStatus;
    }

    public void checkOut() {
        this.checkOutStatus = true;
    }

    public void checkIn() {
        this.checkOutStatus = false;
    }

    @Override
    public String toString(){
        return this.movieName + " " + this.year + " " + this.director + " " + this.rate;
    }
}
