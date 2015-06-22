package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static com.twu.biblioteca.PredefinedMovieDetails.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by qiyuesong on 22/6/15.
 */
public class LibraryItemTest {
    private LibraryItem itemA, itemB;
    @Before
    public void setup() {
        itemA = new LibraryItem(BOOK_FOUR_NAME);
        itemB = new LibraryItem(MOVIE_FOUR_NAME);
    }

    @Test
    public void testIfItemANameIsCorrect() {
        assertEquals(BOOK_FOUR_NAME, itemA.getName());
    }

    @Test
    public void testIfItemBNameIsCorrect() {
        assertEquals(MOVIE_FOUR_NAME, itemB.getName());
    }

    @Test
    public void testItemACheckOutStatusAfterBookAIsCheckedOut(){
        assertFalse(itemA.getCheckOutStatus());
        itemA.checkOut();
        assertTrue(itemA.getCheckOutStatus());
    }

    @Test
    public void testBookBCheckOutStatusAfterBookBIsCheckedOut(){
        assertFalse(itemB.getCheckOutStatus());
        itemB.checkOut();
        assertTrue(itemB.getCheckOutStatus());
    }

    @Test
    public void testBookACheckOutStatusAfterBookAIsReturn(){
        itemA.checkOut();
        assertTrue(itemA.getCheckOutStatus());
        itemA.checkIn();
        assertFalse(itemA.getCheckOutStatus());
    }

    @Test
    public void testBookBCheckOutStatusAfterBookBIsReturn(){
        itemB.checkOut();
        assertTrue(itemB.getCheckOutStatus());
        itemB.checkIn();
        assertFalse(itemB.getCheckOutStatus());
    }

    @Test
    public void testBookAEqualsToBookA(){
        assertEquals(itemA, itemA);
    }

    @Test
    public void testBookADoesNotEqualToBookB(){
        assertNotEquals(itemA, itemB);
    }

    @Test
    public void testBookAToString() {
        assertEquals("Hamlet", itemA.toString());
    }

    @Test
    public void testBookBToString() {
        assertEquals("Lord of the Ring", itemB.toString());
    }
}
