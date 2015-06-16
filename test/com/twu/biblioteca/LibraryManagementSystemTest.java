package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystemTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    private LibraryManagementSystem LMS;
    private  Book bookA, bookB, bookC, bookD, bookE;
    private Customer currentCustomer;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outStream));
        System.setErr(new PrintStream(errStream));

        bookA = new Book("One Hundred Years of Solitude", 1910);
        bookB = new Book("The Old Man and the Sea", 1990);
        bookC = new Book("Programming Pearl", 2003);
        bookD = new Book("Hamlet", 1972);
        bookE = new Book("gone with the wind", 1980);

        LinkedList<Book> booksList = new LinkedList<Book>();
        booksList.add(bookA);
        booksList.add(bookB);
        booksList.add(bookC);
        booksList.add(bookD);
        booksList.add(bookE);
        LMS = new LibraryManagementSystem(booksList);
        currentCustomer = LMS.getCurrentCustomer();
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testCurrentCustomer() {
        assertEquals("Default Customer", currentCustomer.getCustomerName());
    }

    @Test
    public void testShowWelcomeMessage() {
        LMS.showWelcomeMessage();
        assertEquals("Welcome to biblioteca library management system.\nBelow is the operations you can do.", outStream.toString());
    }

    @Test
    public void testShowMainMenu() {
        LMS.showMainMenu();
        assertEquals("List Books\nPlease type in the operation you want to do: ", outStream.toString());
    }

    @Test
    public void testShowAvailableBookList() {
        currentCustomer.borrowBook(bookA);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea 1990\nProgramming Pearl 2003\nHamlet 1972\ngone with the wind 1980", outStream.toString());

        currentCustomer.borrowBook(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea 1990\nHamlet 1972\ngone with the wind 1980", outStream.toString());

        currentCustomer.borrowBook(bookE);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea 1990\nHamlet 1972", outStream.toString());

        currentCustomer.returnBook(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea 1990\nHamlet 1972\nProgramming Pearl 2003", outStream.toString());

        currentCustomer.returnBook(bookA);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea 1990\nHamlet 1972\nProgramming Pearl 2003\nOne Hundred Years of Solitude 1910", outStream.toString());
    }
}
