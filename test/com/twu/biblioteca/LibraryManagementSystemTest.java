package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static com.twu.biblioteca.PredefinedUserDetails.DEFAULT_USERNAME;
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

        bookA = new Book(BOOK_ONE_NAME, BOOK_ONE_AUTHOR, BOOK_ONE_PUBLISHING_YEAR);
        bookB = new Book(BOOK_TWO_NAME, BOOK_TWO_AUTHOR, BOOK_TWO_PUBLISHING_YEAR);
        bookC = new Book(BOOK_THREE_NAME, BOOK_THREE_AUTHOR, BOOK_THREE_PUBLISHING_YEAR);
        bookD = new Book(BOOK_FOUR_NAME, BOOK_FOUR_AUTHOR, BOOK_FOUR_PUBLISHING_YEAR);
        bookE = new Book(BOOK_FIVE_NAME, BOOK_FIVE_AUTHOR, BOOK_FIVE_PUBLISHING_YEAR);

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
        assertEquals(DEFAULT_USERNAME, currentCustomer.getCustomerName());
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
                "Gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.borrowBook(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n" +
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
        assertEquals("main menu", LMS.getSystemCurrentPosition());

        LMS.processMainMenuOperations("sell book");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();
        assertEquals("main menu", LMS.getSystemCurrentPosition());

        LMS.processMainMenuOperations("List Books");
        assertEquals("One Hundred Years of Solitude Gabriel García Márquez 1910\n" +
                "The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        assertEquals("list books", LMS.getSystemCurrentPosition());
    }

    @Test
    public void testProcessBookOperations() {
        LMS.processMainMenuOperations("List Books");
        assertEquals("list books", LMS.getSystemCurrentPosition());
        outStream.reset();

        LMS.processBookOperations("borrow Harry Potter");
        assertEquals("That book is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processBookOperations("borrow Gone with the wind");
        assertEquals("Thank you! Enjoy the book.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(1, currentCustomer.getBorrowedBooksList().size());
        assertTrue(currentCustomer.getBorrowedBooksList().getFirst().getCheckOutStatus());
        assertEquals("Gone with the wind", currentCustomer.getBorrowedBooksList().getFirst().getBookName());

        LMS.processBookOperations("buy Gone with the wind");
        assertEquals("Your operation is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processBookOperations("return Hello Potter");
        assertEquals("That is not a valid book to return.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();


        LMS.processBookOperations("return Gone with the wind");
        assertEquals("Thank you for returning the book.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(0, currentCustomer.getBorrowedBooksList().size());

        LMS.processBookOperations("back");
        assertEquals("main menu", LMS.getSystemCurrentPosition());
    }

    @Test
    public void testFindBookIfAvailable(){
        currentCustomer.borrowBook(bookA);
        assertNull(LMS.findBookIfAvailable(bookA.getBookName().toLowerCase()));
        assertNotNull(LMS.findBookIfAvailable(bookB.getBookName().toLowerCase()));
        currentCustomer.borrowBook(bookB);
        assertNull(LMS.findBookIfAvailable(bookB.getBookName().toLowerCase()));
    }
}
