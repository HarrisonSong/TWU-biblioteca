package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.twu.biblioteca.PredefinedBooksDetails.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class BookTest {
    private Book bookA, bookB;
    @Before
    public void setup() {
        bookA = new Book(BOOK_FOUR_NAME, BOOK_FOUR_AUTHOR, BOOK_FOUR_PUBLISHING_YEAR);
        bookB = new Book(BOOK_FIVE_NAME, BOOK_FIVE_AUTHOR, BOOK_FIVE_PUBLISHING_YEAR);
    }

    @Test
    public void testIdBookNameIsCorrect() {
        assertEquals(BOOK_FOUR_NAME, bookA.getBookName());
        assertEquals(BOOK_FIVE_NAME, bookB.getBookName());
    }

    @Test
    public void testIfBookAuthorIsCorrect() {
        assertEquals(BOOK_FOUR_AUTHOR, bookA.getAuthor());
        assertEquals(BOOK_FIVE_AUTHOR, bookB.getAuthor());
    }

    @Test
    public void testIfBookPublishingTimeIsCorrect() {
        assertEquals(BOOK_FOUR_PUBLISHING_YEAR, bookA.getPublishingYear());
        assertEquals(BOOK_FIVE_PUBLISHING_YEAR, bookB.getPublishingYear());
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
