package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.twu.biblioteca.PredefinedBooksDetails.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class CustomerTest {
    private Customer customer;
    private Book bookA, bookB, bookC;

    @Before
    public void setup() {
        customer = new Customer("Song Qiyue");
        bookA = new Book(BOOK_ONE_NAME, BOOK_ONE_AUTHOR, BOOK_ONE_PUBLISHING_YEAR);
        bookB = new Book(BOOK_TWO_NAME, BOOK_TWO_AUTHOR, BOOK_TWO_PUBLISHING_YEAR);
        bookC = new Book(BOOK_THREE_NAME, BOOK_THREE_AUTHOR, BOOK_THREE_PUBLISHING_YEAR);
    }

    @Test
    public void testGetCustomerName() {
        assertEquals("Song Qiyue", customer.getCustomerName());
    }

    @Test
    public void testCustomerBorrowedBooksListIsEmpty() {
        assertEquals(0, customer.getBorrowedBooksList().size());
    }

    @Test
    public void testCustomerBorrowBook() {
        customer.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customer.getBorrowedBooksList().size());
        assertEquals(bookA,customer.getBorrowedBooksList().getLast());

        customer.borrowBook(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customer.getBorrowedBooksList().size());
        assertEquals(bookB,customer.getBorrowedBooksList().getLast());

        customer.borrowBook(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(3, customer.getBorrowedBooksList().size());
        assertEquals(bookC, customer.getBorrowedBooksList().getLast());

        customer.borrowBook(bookA);
        assertEquals(3, customer.getBorrowedBooksList().size());
        assertEquals(bookC, customer.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerReturnBook() {
        customer.borrowBook(bookA);
        customer.borrowBook(bookB);
        customer.borrowBook(bookC);

        customer.returnBook(bookA);
        assertFalse(bookA.getCheckOutStatus());
        assertEquals(2, customer.getBorrowedBooksList().size());

        customer.returnBook(bookB);
        assertFalse(bookB.getCheckOutStatus());
        assertEquals(1, customer.getBorrowedBooksList().size());

        customer.returnBook(bookA);
        assertEquals(1, customer.getBorrowedBooksList().size());

        customer.returnBook(bookC);
        assertFalse(bookC.getCheckOutStatus());
        assertEquals(0, customer.getBorrowedBooksList().size());
    }

    @Test
    public void testFindBookIfAvailableToReturn(){
        customer.borrowBook(bookA);
        customer.borrowBook(bookC);
        assertNull(customer.findBookIfAvailableToReturn(bookB.getBookName().toLowerCase()));
        assertNotNull(customer.findBookIfAvailableToReturn(bookA.getBookName().toLowerCase()));
        assertNotNull(customer.findBookIfAvailableToReturn(bookC.getBookName().toLowerCase()));
    }
}
