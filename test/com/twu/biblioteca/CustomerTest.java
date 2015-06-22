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
        customerA = new Customer(CUSTOMER_ONE_NAME, CUSTOMER_ONE_EMAIL, CUSTOMER_ONE_PHONENUMBER);
        customerB = new Customer(CUSTOMER_TWO_NAME, CUSTOMER_TWO_EMAIL, CUSTOMER_TWO_PHONENUMBER);
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
    public void testCustomerAEmail() {
        assertEquals(CUSTOMER_ONE_EMAIL, customerA.getEmail());
    }

    @Test
    public void testCustomerBEmail() {
        assertEquals(CUSTOMER_TWO_EMAIL, customerB.getEmail());
    }

    @Test
    public void testCustomerAPhoneNumber() {
        assertEquals(CUSTOMER_ONE_PHONENUMBER, customerA.getPhoneNumber());
    }

    @Test
    public void testCustomerBPhoneNumber() {
        assertEquals(CUSTOMER_TWO_PHONENUMBER, customerB.getPhoneNumber());
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
        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerABorrowBookAAndBookB() {
        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());

        customerA.borrowItem(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedBooksList().size());
        assertEquals(bookB, customerA.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerABorrowBookAAndBorrowBookAAgain() {
        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());

        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertEquals(bookA, customerA.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerBBorrowBookABookBAndBookC() {
        customerB.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedBooksList().size());
        assertEquals(bookA, customerB.getBorrowedBooksList().getLast());

        customerB.borrowItem(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertEquals(bookB, customerB.getBorrowedBooksList().getLast());

        customerB.borrowItem(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(3, customerB.getBorrowedBooksList().size());
        assertEquals(bookC, customerB.getBorrowedBooksList().getLast());

        customerB.borrowItem(bookA);
        assertEquals(3, customerB.getBorrowedBooksList().size());
        assertEquals(bookC, customerB.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerBBorrowBookAAndBookCAndBorrowBookAAgain() {
        customerB.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedBooksList().size());
        assertEquals(bookA, customerB.getBorrowedBooksList().getLast());

        customerB.borrowItem(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertEquals(bookC,customerB.getBorrowedBooksList().getLast());

        customerB.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertEquals(bookC, customerB.getBorrowedBooksList().getLast());
    }

    @Test
    public void testCustomerAReturnBookA() {
        customerA.borrowItem(bookA);
        customerA.borrowItem(bookB);
        customerA.borrowItem(bookC);
        customerA.returnItem(bookA);

        assertFalse(bookA.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedBooksList().size());
        assertTrue(customerA.getBorrowedBooksList().contains(bookB));
        assertTrue(customerA.getBorrowedBooksList().contains(bookC));
    }

    @Test
    public void testCustomerAReturnBookAAndBookC(){
        customerA.borrowItem(bookA);
        customerA.borrowItem(bookB);
        customerA.borrowItem(bookC);
        customerA.returnItem(bookA);
        customerA.returnItem(bookC);

        assertFalse(bookA.getCheckOutStatus());
        assertFalse(bookC.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedBooksList().size());
        assertTrue(customerA.getBorrowedBooksList().contains(bookB));
    }

    @Test
    public void testCustomerBReturnBookBAndReturnBookBAgain(){
        customerB.borrowItem(bookA);
        customerB.borrowItem(bookB);
        customerB.borrowItem(bookC);
        customerB.returnItem(bookB);
        customerB.returnItem(bookB);

        assertFalse(bookB.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedBooksList().size());
        assertTrue(customerB.getBorrowedBooksList().contains(bookA));
        assertTrue(customerB.getBorrowedBooksList().contains(bookC));
    }

    @Test
    public void testCustomerBReturnBookBAndBookCAndReturnBookBAgain(){
        customerB.borrowItem(bookA);
        customerB.borrowItem(bookB);
        customerB.borrowItem(bookC);
        customerB.returnItem(bookB);
        customerB.returnItem(bookC);
        customerB.returnItem(bookB);

        assertFalse(bookB.getCheckOutStatus());
        assertFalse(bookC.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedBooksList().size());
        assertTrue(customerB.getBorrowedBooksList().contains(bookA));
    }

    @Test
    public void testCustomerAFindBookACanBeReturnAfterBorrowBookA(){
        customerA.borrowItem(bookA);
        assertNotNull(customerA.findBookIfAvailableToReturn(bookA.getName()));
    }

    @Test
    public void testCustomerAFindBookACanBeReturnAfterBorrowBookAAndBookC(){
        customerA.borrowItem(bookA);
        customerA.borrowItem(bookC);
        assertNotNull(customerA.findBookIfAvailableToReturn(bookA.getName()));
    }

    @Test
    public void testCustomerAFindBookBCanNotBeReturnAfterBorrowBookAAndBookC(){
        customerA.borrowItem(bookA);
        customerA.borrowItem(bookC);
        assertNull(customerA.findBookIfAvailableToReturn(bookB.getName()));
    }

    @Test
    public void testCustomerBFindBookAAndBookBCanBeReturnAfterBorrowBookABookBAndBookC(){
        customerB.borrowItem(bookA);
        customerB.borrowItem(bookB);
        customerB.borrowItem(bookC);
        assertNotNull(customerB.findBookIfAvailableToReturn(bookA.getName()));
        assertNotNull(customerB.findBookIfAvailableToReturn(bookB.getName()));
    }

    @Test
    public void testCustomerBFindBookCCanNotBeReturnAfterBorrowBookAAndBookB(){
        customerA.borrowItem(bookA);
        customerA.borrowItem(bookB);
        assertNull(customerB.findBookIfAvailableToReturn(bookC.getName()));
    }
}
