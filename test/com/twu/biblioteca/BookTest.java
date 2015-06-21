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
    public void testIfBookANameIsCorrect() {
        assertEquals(BOOK_FOUR_NAME, bookA.getBookName());
    }

    @Test
    public void testIfBookBNameIsCorrect() {
        assertEquals(BOOK_FIVE_NAME, bookB.getBookName());
    }

    @Test
    public void testIfBookAAuthorIsCorrect() {
        assertEquals(BOOK_FOUR_AUTHOR, bookA.getAuthor());
    }

    @Test
    public void testIfBookBAAuthorIsCorrect() {
        assertEquals(BOOK_FIVE_AUTHOR, bookB.getAuthor());
    }

    @Test
    public void testIfBookAPublishingTimeIsCorrect() {
        assertEquals(BOOK_FOUR_PUBLISHING_YEAR, bookA.getPublishingYear());
    }

    @Test
    public void testIfBookBPublishingTimeIsCorrect() {
        assertEquals(BOOK_FIVE_PUBLISHING_YEAR, bookB.getPublishingYear());
    }

    @Test
    public void testBookACheckOutStatusAfterBookAIsCheckedOut(){
        assertFalse(bookA.getCheckOutStatus());
        bookA.checkOut();
        assertTrue(bookA.getCheckOutStatus());
    }

    @Test
    public void testBookBCheckOutStatusAfterBookBIsCheckedOut(){
        assertFalse(bookB.getCheckOutStatus());
        bookB.checkOut();
        assertTrue(bookB.getCheckOutStatus());
    }

    @Test
    public void testBookACheckOutStatusAfterBookAIsReturn(){
        bookA.checkOut();
        assertTrue(bookA.getCheckOutStatus());
        bookA.checkIn();
        assertFalse(bookA.getCheckOutStatus());
    }

    @Test
    public void testBookBCheckOutStatusAfterBookBIsReturn(){
        bookB.checkOut();
        assertTrue(bookB.getCheckOutStatus());
        bookB.checkIn();
        assertFalse(bookB.getCheckOutStatus());
    }

    @Test
    public void testBookAEqualsToBookA(){
        assertEquals(bookA, bookA);
    }

    @Test
    public void testBookADoesNotEqualToBookB(){
        assertNotEquals(bookA, bookB);
    }

    @Test
    public void testBookAToString() {
        assertEquals("Hamlet Shakespeare 1972", bookA.toString());
    }
}
