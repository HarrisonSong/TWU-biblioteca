package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static org.junit.Assert.*;
import static com.twu.biblioteca.PredefinedUserDetails.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class CustomerTest {
    private Customer customerA, customerB;
    private Book bookA, bookB, bookC;

    @Before
    public void setup() {
        customerA = new Customer(CUSTOMER_ONE_NAME);
        customerB = new Customer(CUSTOMER_TWO_NAME);
        bookA = new Book(BOOK_ONE_NAME, BOOK_ONE_AUTHOR, BOOK_ONE_PUBLISHING_YEAR);
        bookB = new Book(BOOK_TWO_NAME, BOOK_TWO_AUTHOR, BOOK_TWO_PUBLISHING_YEAR);
        bookC = new Book(BOOK_THREE_NAME, BOOK_THREE_AUTHOR, BOOK_THREE_PUBLISHING_YEAR);
    }

    @Test
    public void testCustomerAName() {
        assertEquals(CUSTOMER_ONE_NAME, customerA.getCustomerName());
    }

    @Test
    public void testCustomerBName() {
        assertEquals(CUSTOMER_TWO_NAME, customerB.getCustomerName());
    }

    @Test
    public void testCustomerAInitialBorrowedBooksListIsEmpty() {
        assertEquals(0, customerA.getBorrowedBooksList().size());
    }

    @Test
    public void testCustomerBInitialBorrowedBooksListIsEmpty() {
        assertEquals(0, customerB.getBorrowedBooksList().size());
    }

    @Test
    public void testCustomerBorrowBook() {
        customerA.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA,customerA.getBorrowedBooksList().getLast());

        customerA.borrowBook(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedBooksList().size());
        assertEquals(bookB,customerA.getBorrowedBooksList().getLast());

        customerA.borrowBook(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(3, customerA.getBorrowedBooksList().size());
        assertEquals(bookC, customerA.getBorrowedBooksList().getLast());

        customerA.borrowBook(bookA);
        assertEquals(3, customerA.getBorrowedBooksList().size());
        assertEquals(bookC, customerA.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerReturnBook() {
        customerA.borrowBook(bookA);
        customerA.borrowBook(bookB);
        customerA.borrowBook(bookC);

        customerA.returnBook(bookA);
        assertFalse(bookA.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedBooksList().size());

        customerA.returnBook(bookB);
        assertFalse(bookB.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());

        customerA.returnBook(bookA);
        assertEquals(1, customerA.getBorrowedBooksList().size());

        customerA.returnBook(bookC);
        assertFalse(bookC.getCheckOutStatus());
        assertEquals(0, customerA.getBorrowedBooksList().size());
    }

    @Test
    public void testFindBookIfAvailableToReturn(){
        customerA.borrowBook(bookA);
        customerA.borrowBook(bookC);
        assertNull(customerA.findBookIfAvailableToReturn(bookB.getBookName().toLowerCase()));
        assertNotNull(customerA.findBookIfAvailableToReturn(bookA.getBookName().toLowerCase()));
        assertNotNull(customerA.findBookIfAvailableToReturn(bookC.getBookName().toLowerCase()));
    }
}
