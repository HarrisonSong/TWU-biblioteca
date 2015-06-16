package com.twu.bibioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class BookTest {
    private Book bookA, bookB;
    @Before
    public void setup() {
        bookA = new Book("Hamlet", 1972);
        bookB = new Book("gone with the wind", 1980);
    }

    @Test
    public void testIfBookAuthorIsCorrect() {
        assertEquals("Hamlet", bookA.getAuthor());
        assertEquals("gone with the wind", bookB.getAuthor());
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
}
