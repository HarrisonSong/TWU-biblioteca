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
    public void testCustomerABorrowBookA() {
        customerA.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerABorrowBookAAndBookB() {
        customerA.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());

        customerA.borrowBook(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedBooksList().size());
        assertEquals(bookB, customerA.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerABorrowBookAAndBorrowBookAAgain() {
        customerA.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());

        customerA.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerBBorrowBookABookBAndBookC() {
        customerB.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedBooksList().size());
        assertEquals(bookA, customerB.getBorrowedBooksList().getLast());

        customerB.borrowBook(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertEquals(bookB, customerB.getBorrowedBooksList().getLast());

        customerB.borrowBook(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(3, customerB.getBorrowedBooksList().size());
        assertEquals(bookC, customerB.getBorrowedBooksList().getLast());

        customerB.borrowBook(bookA);
        assertEquals(3, customerB.getBorrowedBooksList().size());
        assertEquals(bookC, customerB.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerBBorrowBookAAndBookCAndBorrowBookAAgain() {
        customerB.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedBooksList().size());
        assertEquals(bookA, customerB.getBorrowedBooksList().getLast());

        customerB.borrowBook(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertEquals(bookC,customerB.getBorrowedBooksList().getLast());

        customerB.borrowBook(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertEquals(bookC, customerB.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerAReturnBookA() {
        customerA.borrowBook(bookA);
        customerA.borrowBook(bookB);
        customerA.borrowBook(bookC);
        customerA.returnBook(bookA);

        assertFalse(bookA.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedBooksList().size());
        assertTrue(customerA.getBorrowedBooksList().contains(bookB));
        assertTrue(customerA.getBorrowedBooksList().contains(bookC));
    }

    @Test
    public void testCustomerAReturnBookAAndBookC(){
        customerA.borrowBook(bookA);
        customerA.borrowBook(bookB);
        customerA.borrowBook(bookC);
        customerA.returnBook(bookA);
        customerA.returnBook(bookC);

        assertFalse(bookA.getCheckOutStatus());
        assertFalse(bookC.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertTrue(customerA.getBorrowedBooksList().contains(bookB));
    }

    @Test
    public void testCustomerBReturnBookBAndReturnBookBAgain(){
        customerB.borrowBook(bookA);
        customerB.borrowBook(bookB);
        customerB.borrowBook(bookC);
        customerB.returnBook(bookB);
        customerB.returnBook(bookB);

        assertFalse(bookB.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertTrue(customerB.getBorrowedBooksList().contains(bookA));
        assertTrue(customerB.getBorrowedBooksList().contains(bookC));
    }

    @Test
    public void testCustomerBReturnBookBAndBookCAndReturnBookBAgain(){
        customerB.borrowBook(bookA);
        customerB.borrowBook(bookB);
        customerB.borrowBook(bookC);
        customerB.returnBook(bookB);
        customerB.returnBook(bookC);
        customerB.returnBook(bookB);

        assertFalse(bookB.getCheckOutStatus());
        assertFalse(bookC.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedBooksList().size());
        assertTrue(customerB.getBorrowedBooksList().contains(bookA));
    }

    @Test
    public void testCustomerAFindBookACanBeReturnAfterBorrowBookA(){
        customerA.borrowBook(bookA);
        assertNotNull(customerA.findBookIfAvailableToReturn(bookA.getBookName()));
    }

    @Test
    public void testCustomerAFindBookACanBeReturnAfterBorrowBookAAndBookC(){
        customerA.borrowBook(bookA);
        customerA.borrowBook(bookC);
        assertNotNull(customerA.findBookIfAvailableToReturn(bookA.getBookName()));
    }

    @Test
    public void testCustomerAFindBookBCanNotBeReturnAfterBorrowBookAAndBookC(){
        customerA.borrowBook(bookA);
        customerA.borrowBook(bookC);
        assertNull(customerA.findBookIfAvailableToReturn(bookB.getBookName()));
    }

    @Test
    public void testCustomerBFindBookAAndBookBCanBeReturnAfterBorrowBookABookBAndBookC(){
        customerB.borrowBook(bookA);
        customerB.borrowBook(bookB);
        customerB.borrowBook(bookC);
        assertNotNull(customerB.findBookIfAvailableToReturn(bookA.getBookName()));
        assertNotNull(customerB.findBookIfAvailableToReturn(bookB.getBookName()));
    }

    @Test
    public void testCustomerBFindBookCCanNotBeReturnAfterBorrowBookAAndBookB(){
        customerA.borrowBook(bookA);
        customerA.borrowBook(bookB);
        assertNull(customerB.findBookIfAvailableToReturn(bookC.getBookName()));
    }
}
