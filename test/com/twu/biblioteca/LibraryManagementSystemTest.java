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

        bookA = new Book("One Hundred Years of Solitude", "Gabriel García Márquez", 1910);
        bookB = new Book("The Old Man and the Sea", "Ernest Hemingway", 1990);
        bookC = new Book("Programming Pearl", "Jon Bentley", 2003);
        bookD = new Book("Hamlet", "Shakespeare", 1972);
        bookE = new Book("gone with the wind", "Margaret Mitchell", 1980);

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
        assertEquals("Welcome to biblioteca library management system.\nBelow is the operations you can do.\n", outStream.toString());
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
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\nProgramming Pearl Jon Bentley 2003\nHamlet Shakespeare 1972\ngone with the wind Margaret Mitchell 1980\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.borrowBook(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\nHamlet Shakespeare 1972\ngone with the wind Margaret Mitchell 1980\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.borrowBook(bookE);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\nHamlet Shakespeare 1972\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.returnBook(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\nProgramming Pearl Jon Bentley 2003\nHamlet Shakespeare 1972\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.returnBook(bookA);
        LMS.showBooksList();
        assertEquals("One Hundred Years of Solitude Gabriel García Márquez 1910\nThe Old Man and the Sea Ernest Hemingway 1990\nProgramming Pearl Jon Bentley 2003\nHamlet Shakespeare 1972\nPlease type in the operation you want to do: ", outStream.toString());
    }

    @Test
    public void testShowFlashMessage() {
        LMS.showFlashMessage("invalid Menu option");
        assertEquals("Select a valid option!", outStream.toString());

        LMS.showFlashMessage("successful checkout");
        assertEquals("Thank you! Enjoy the book.", outStream.toString());

        LMS.showFlashMessage("unsuccessful checkout");
        assertEquals("That book is not available.", outStream.toString());

        LMS.showFlashMessage("successful return");
        assertEquals("Thank you for returning the book.", outStream.toString());

        LMS.showFlashMessage("unsuccessful return");
        assertEquals("That is not a valid book to return.", outStream.toString());
    }
}
