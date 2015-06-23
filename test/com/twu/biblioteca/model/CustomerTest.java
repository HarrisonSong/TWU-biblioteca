package com.twu.biblioteca.model;

import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static com.twu.biblioteca.PredefinedUserDetails.*;
import static org.junit.Assert.*;

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
    public void testCustomerLogin(){
        assertFalse(customerA.isLoggedIn());
        customerA.login();
        assertTrue(customerA.isLoggedIn());
    }

    @Test
    public void testCustomerAInitialBorrowedBooksListIsEmpty() {
        assertEquals(0, customerA.getBorrowedItemsList().size());
    }

    @Test
    public void testCustomerBInitialBorrowedBooksListIsEmpty() {
        assertEquals(0, customerB.getBorrowedItemsList().size());
    }

    @Test
    public void testCustomerABorrowBookA() {
        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedItemsList().size());
        assertEquals(bookA, customerA.getBorrowedItemsList().getLast());
    }

    @Test
    public void testCustomerABorrowBookAAndBookB() {
        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedItemsList().size());
        assertEquals(bookA, customerA.getBorrowedItemsList().getLast());

        customerA.borrowItem(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedItemsList().size());
        assertEquals(bookB, customerA.getBorrowedItemsList().getLast());
    }

    @Test
    public void testCustomerABorrowBookAAndBorrowBookAAgain() {
        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedItemsList().size());
        assertEquals(bookA, customerA.getBorrowedItemsList().getLast());

        customerA.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerA.getBorrowedItemsList().size());
        assertEquals(bookA, customerA.getBorrowedItemsList().getLast());
    }

    @Test
    public void testCustomerBBorrowBookABookBAndBookC() {
        customerB.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedItemsList().size());
        assertEquals(bookA, customerB.getBorrowedItemsList().getLast());

        customerB.borrowItem(bookB);
        assertTrue(bookB.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedItemsList().size());
        assertEquals(bookB, customerB.getBorrowedItemsList().getLast());

        customerB.borrowItem(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(3, customerB.getBorrowedItemsList().size());
        assertEquals(bookC, customerB.getBorrowedItemsList().getLast());

        customerB.borrowItem(bookA);
        assertEquals(3, customerB.getBorrowedItemsList().size());
        assertEquals(bookC, customerB.getBorrowedItemsList().getLast());
    }

    @Test
    public void testCustomerBBorrowBookAAndBookCAndBorrowBookAAgain() {
        customerB.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(1, customerB.getBorrowedItemsList().size());
        assertEquals(bookA, customerB.getBorrowedItemsList().getLast());

        customerB.borrowItem(bookC);
        assertTrue(bookC.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedItemsList().size());
        assertEquals(bookC,customerB.getBorrowedItemsList().getLast());

        customerB.borrowItem(bookA);
        assertTrue(bookA.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedItemsList().size());
        assertEquals(bookC, customerB.getBorrowedItemsList().getLast());
    }

    @Test
    public void testCustomerAReturnBookA() {
        customerA.borrowItem(bookA);
        customerA.borrowItem(bookB);
        customerA.borrowItem(bookC);
        customerA.returnItem(bookA);

        assertFalse(bookA.getCheckOutStatus());
        assertEquals(2, customerA.getBorrowedItemsList().size());
        assertTrue(customerA.getBorrowedItemsList().contains(bookB));
        assertTrue(customerA.getBorrowedItemsList().contains(bookC));
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
        assertEquals(1, customerA.getBorrowedItemsList().size());
        assertTrue(customerA.getBorrowedItemsList().contains(bookB));
    }

    @Test
    public void testCustomerBReturnBookBAndReturnBookBAgain(){
        customerB.borrowItem(bookA);
        customerB.borrowItem(bookB);
        customerB.borrowItem(bookC);
        customerB.returnItem(bookB);
        customerB.returnItem(bookB);

        assertFalse(bookB.getCheckOutStatus());
        assertEquals(2, customerB.getBorrowedItemsList().size());
        assertTrue(customerB.getBorrowedItemsList().contains(bookA));
        assertTrue(customerB.getBorrowedItemsList().contains(bookC));
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
        assertEquals(1, customerB.getBorrowedItemsList().size());
        assertTrue(customerB.getBorrowedItemsList().contains(bookA));
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

    @Test
    public void testCustomerAToString(){
        assertEquals("Li Lei\n" +
                "harrisonsong1991@gmail.com\n" +
                "98826095", customerA.toString());
    }

    @Test
    public void testCustomerBToString(){
        assertEquals("Han Meimei\n" +
                "songqiyue1991@hotmail.com\n" +
                "82805075", customerB.toString());
    }
}
