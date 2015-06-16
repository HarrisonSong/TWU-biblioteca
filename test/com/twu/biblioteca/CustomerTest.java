package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class CustomerTest {
    private Customer customer;
    private Book bookA, bookB, bookC;

    @Before
    public void setup() {
        customer = new Customer("Song Qiyue");
        bookA = new Book("One Hundred Years of Solitude", 1910);
        bookB = new Book("The Old Man and the Sea", 1990);
        bookC = new Book("Programming Pearl", 2003);
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
}
