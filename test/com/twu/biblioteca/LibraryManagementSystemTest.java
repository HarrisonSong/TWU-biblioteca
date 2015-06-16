package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.Assert.*;

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
        assertEquals("Welcome to biblioteca library management system.\n" +
                "Below is the operations you can do.\n", outStream.toString());
    }

    @Test
    public void testShowMainMenu() {
        LMS.showMainMenu();
        assertEquals("List Books\n" +
                "Please type in the operation you want to do: ", outStream.toString());
    }

    @Test
    public void testShowAvailableBookList() {
        currentCustomer.borrowBook(bookA);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.borrowBook(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Hamlet Shakespeare 1972\n" +
                "gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.borrowBook(bookE);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.returnBook(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.returnBook(bookA);
        LMS.showBooksList();
        assertEquals("One Hundred Years of Solitude Gabriel García Márquez 1910\n" +
                "The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
    }

    @Test
    public void testShowFlashMessage() {
        LMS.showFlashMessage("invalid menu option");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();

        LMS.showFlashMessage("successful checkout");
        assertEquals("Thank you! Enjoy the book.\n", outStream.toString());
        outStream.reset();

        LMS.showFlashMessage("unsuccessful checkout");
        assertEquals("That book is not available.\n", outStream.toString());
        outStream.reset();

        LMS.showFlashMessage("successful return");
        assertEquals("Thank you for returning the book.\n", outStream.toString());
        outStream.reset();

        LMS.showFlashMessage("unsuccessful return");
        assertEquals("That is not a valid book to return.\n", outStream.toString());
        outStream.reset();

        LMS.showFlashMessage("invalid book option");
        assertEquals("Your operation is not available.\n", outStream.toString());
    }

    @Test
    public void testProcessMainMenuOperations(){
        LMS.processMainMenuOperations("buy book");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();

        LMS.processMainMenuOperations("sell book");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();

        LMS.processMainMenuOperations("List Books");
        assertEquals("One Hundred Years of Solitude Gabriel García Márquez 1910\n" +
                "The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processMainMenuOperations("list books");
        assertEquals("One Hundred Years of Solitude Gabriel García Márquez 1910\n" +
                "The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();
    }

    @Test
    public void testProcessBookOperations() {
        LMS.processBookOperations("borrow Harry Potter");
        assertEquals("That book is not available.\n", outStream.toString());
        outStream.reset();

        LMS.processBookOperations("borrow gone with the wind");
        assertEquals("Thank you! Enjoy the book.\n", outStream.toString());
        outStream.reset();
        assertEquals(1, currentCustomer.getBorrowedBooksList().size());
        assertTrue(currentCustomer.getBorrowedBooksList().getFirst().getCheckOutStatus());
        assertEquals("gone with the wind", currentCustomer.getBorrowedBooksList().getFirst().getBookName());

        LMS.processBookOperations("buy gone with the wind");
        assertEquals("Your operation is not available.\n", outStream.toString());
        outStream.reset();

        LMS.processBookOperations("return Hello Potter");
        assertEquals("That is not a valid book to return.\n", outStream.toString());
        outStream.reset();


        LMS.processBookOperations("return gone with the wind");
        assertEquals("Thank you for returning the book.\n", outStream.toString());
        outStream.reset();
        assertEquals(0, currentCustomer.getBorrowedBooksList().size());
    }

    @Test
    public void testFindBookIfAvailable(){
        currentCustomer.borrowBook(bookA);
        assertNull(LMS.findBookIfAvailable(bookA.getBookName()));
        assertNotNull(LMS.findBookIfAvailable(bookB.getBookName()));
        currentCustomer.borrowBook(bookB);
        assertNull(LMS.findBookIfAvailable(bookB.getBookName()));
    }
}
