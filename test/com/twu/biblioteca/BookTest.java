package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class BookTest {
    private Book bookA, bookB;
    @Before
    public void setup() {
        bookA = new Book("Hamlet", "Shakespeare", 1972);
        bookB = new Book("gone with the wind", "Margaret Mitchell", 1980);
    }

    @Test
    public void testIdBookNameIsCorrect() {
        assertEquals("Hamlet", bookA.getBookName());
        assertEquals("gone with the wind", bookB.getBookName());
    }

    @Test
    public void testIfBookAuthorIsCorrect() {
        assertEquals("Shakespeare", bookA.getAuthor());
        assertEquals("Margaret Mitchell", bookB.getAuthor());
    }

    @Test
    public void testIfBookPublishingTimeIsCorrect() {
        assertEquals(1972, bookA.getPublishingYear());
        assertEquals(1980, bookB.getPublishingYear());
    }

    @Test
    public void testBookCheckoutAndCheckInOperations() {
        assertFalse(bookA.getCheckOutStatus());
        bookA.checkOut();
        assertTrue(bookA.getCheckOutStatus());
        bookA.checkIn();
        assertFalse(bookA.getCheckOutStatus());

        assertFalse(bookB.getCheckOutStatus());
        bookB.checkOut();
        assertTrue(bookB.getCheckOutStatus());
        bookB.checkIn();
        assertFalse(bookB.getCheckOutStatus());
    }

    @Test
    public void testBookEquality() {
        assertEquals(bookA, bookA);
        assertNotEquals(bookA, bookB);
    }
}
