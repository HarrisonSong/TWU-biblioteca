package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.twu.biblioteca.PredefinedMovieDetails.*;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class MovieTest {
    private Movie movieA, movieB;
    @Before
    public void setup() {
        movieA = new Movie(MOVIE_THREE_NAME, MOVIE_THREE_YEAR, MOVIE_THREE_DIRECTOR, MOVIE_THREE_RATING);
        movieB = new Movie(MOVIE_FOUR_NAME, MOVIE_FOUR_YEAR, MOVIE_FOUR_DIRECTOR, MOVIE_FOUR_RATING);
    }

    @Test
    public void testIfMovieANameIsCorrect() {
        assertEquals(MOVIE_THREE_NAME, movieA.getName());
    }

    @Test
    public void testIfMovieBNameIsCorrect() {
        assertEquals(MOVIE_FOUR_NAME, movieB.getName());
    }

    @Test
    public void testIfMovieAYearIsCorrect() {
        assertEquals(MOVIE_THREE_YEAR, movieA.getMovieYear());
    }

    @Test
    public void testIfMovieBYearIsCorrect() {
        assertEquals(MOVIE_FOUR_YEAR, movieB.getMovieYear());
    }

    @Test
    public void testIfMovieADirectorIsCorrect() {
        assertEquals(MOVIE_THREE_DIRECTOR, movieA.getMovieDirector());
    }

    @Test
    public void testIfMovieBDirectorIsCorrect() {
        assertEquals(MOVIE_FOUR_DIRECTOR, movieB.getMovieDirector());
    }

    @Test
    public void testIfMovieARateIsCorrect() {
        assertEquals(MOVIE_THREE_RATING, movieA.getMovieRate());
    }

    @Test
    public void testIfMovieBRateIsCorrect() {
        assertEquals(MOVIE_FOUR_RATING, movieB.getMovieRate());
    }

    @Test
    public void testMovieACheckOutStatusAfterMovieAIsCheckedOut(){
        assertFalse(movieA.getCheckOutStatus());
        movieA.checkOut();
        assertTrue(movieA.getCheckOutStatus());
    }

    @Test
    public void testMovieBCheckOutStatusAfterMovieBIsCheckedOut(){
        assertFalse(movieB.getCheckOutStatus());
        movieB.checkOut();
        assertTrue(movieB.getCheckOutStatus());
    }

    @Test
    public void testMovieACheckOutStatusAfterMovieAIsReturn(){
        movieA.checkOut();
        assertTrue(movieA.getCheckOutStatus());
        movieA.checkIn();
        assertFalse(movieA.getCheckOutStatus());
    }

    @Test
    public void testMovieBCheckOutStatusAfterMovieBIsReturn(){
        movieB.checkOut();
        assertTrue(movieB.getCheckOutStatus());
        movieB.checkIn();
        assertFalse(movieB.getCheckOutStatus());
    }

    @Test
    public void testMovieAEqualsToMovieA(){
        assertEquals(movieA, movieA);
    }

    @Test
    public void testMovieADoesNotEqualToMovieB(){
        assertNotEquals(movieA, movieB);
    }

    @Test
    public void testMovieAToString() {
        assertEquals("007 2001 Louis 7", movieA.toString());
    }

    @Test
    public void testMovieBToString() {
        assertEquals("Lord of the Ring 2002 Tom 8", movieB.toString());
    }
}
