package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static com.twu.biblioteca.MainMenuOptionConstants.*;
import static com.twu.biblioteca.PredefinedMovieDetails.*;
import static org.junit.Assert.*;
import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static com.twu.biblioteca.PredefinedUserDetails.*;
import static com.twu.biblioteca.UserSystemPositions.*;
import static com.twu.biblioteca.SystemOptionConstants.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystemTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    private ByteArrayInputStream inStream;
    private LibraryManagementSystem LMS;
    private Book bookA, bookB, bookC, bookD, bookE;
    private Movie movieA, movieB, movieC, movieD;
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

        movieA = new Movie(MOVIE_ONE_NAME, MOVIE_ONE_YEAR, MOVIE_ONE_DIRECTOR, MOVIE_ONE_RATING);
        movieB = new Movie(MOVIE_TWO_NAME, MOVIE_TWO_YEAR, MOVIE_TWO_DIRECTOR, MOVIE_TWO_RATING);
        movieC = new Movie(MOVIE_THREE_NAME, MOVIE_THREE_YEAR, MOVIE_THREE_DIRECTOR, MOVIE_THREE_RATING);
        movieD = new Movie(MOVIE_FOUR_NAME, MOVIE_FOUR_YEAR, MOVIE_FOUR_DIRECTOR, MOVIE_FOUR_RATING);

        LinkedList<LibraryItem> itemsList = new LinkedList<LibraryItem>();
        itemsList.add(bookA);
        itemsList.add(bookB);
        itemsList.add(bookC);
        itemsList.add(bookD);
        itemsList.add(bookE);
        itemsList.add(movieA);
        itemsList.add(movieB);
        itemsList.add(movieC);
        itemsList.add(movieD);

        LinkedList<String> menuList = new LinkedList<String>();
        menuList.add(MAIN_MENU_LIST_BOOKS_OPTION);
        menuList.add(MAIN_MENU_LIST_MOVIES_OPTION);
        LMS = new LibraryManagementSystem(itemsList, menuList);
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
    public void testShowAvailableBookList() {
        currentCustomer.borrowItem(bookA);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.borrowItem(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.borrowItem(bookE);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.returnItem(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        currentCustomer.returnItem(bookA);
        LMS.showBooksList();
        assertEquals("One Hundred Years of Solitude Gabriel García Márquez 1910\n" +
                "The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
    }

    @Test
    public void testProcessMainMenuOperationsForListBooks(){
        LMS.processMainMenuOperations("buy book");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());

        LMS.processMainMenuOperations("sell book");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());

        LMS.processMainMenuOperations("List Books");
        assertEquals("One Hundred Years of Solitude Gabriel García Márquez 1910\n" +
                "The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        assertEquals(SYSTEM_POSITION_LIST_BOOKS, LMS.getSystemCurrentPosition());
    }

    @Test
    public void testProcessMainMenuOperationsForListMovies(){
        LMS.processMainMenuOperations("buy movie");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());

        LMS.processMainMenuOperations("sell movie");
        assertEquals("Select a valid option!\n", outStream.toString());
        outStream.reset();
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());

        LMS.processMainMenuOperations("List Movies");
        assertEquals("Star War 1996 Jim 9\n" +
                "Jurassic Park 1990 Spielberg 7\n" +
                "007 2001 Louis 7\n" +
                "Lord of the Ring 2002 Tom 8\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        assertEquals(SYSTEM_POSITION_LIST_MOVIES, LMS.getSystemCurrentPosition());
    }

    @Test
    public void testProcessLibraryOperationsForBooks() {
        LMS.processMainMenuOperations("List Books");
        assertEquals(SYSTEM_POSITION_LIST_BOOKS, LMS.getSystemCurrentPosition());
        outStream.reset();

        LMS.processLibraryOperations("borrow Harry Potter");
        assertEquals("That book is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("borrow Gone with the wind");
        assertEquals("Thank you! Enjoy the book.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(1, currentCustomer.getBorrowedBooksList().size());
        assertTrue(currentCustomer.getBorrowedBooksList().getFirst().getCheckOutStatus());
        assertEquals("Gone with the wind", currentCustomer.getBorrowedBooksList().getFirst().getName());

        LMS.processLibraryOperations("buy Gone with the wind");
        assertEquals("Your operation is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("return Hello Potter");
        assertEquals("That is not a valid book to return.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();


        LMS.processLibraryOperations("return Gone with the wind");
        assertEquals("Thank you for returning the book.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(0, currentCustomer.getBorrowedBooksList().size());

        LMS.processLibraryOperations(SYSTEM_OPTION_BACK);
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());
    }

    @Test
    public void testProcessLibraryOperationsForMovies() {
        LMS.processMainMenuOperations("List Books");
        assertEquals(SYSTEM_POSITION_LIST_BOOKS, LMS.getSystemCurrentPosition());
        outStream.reset();

        LMS.processLibraryOperations("borrow Harry Potter");
        assertEquals("That book is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("borrow Gone with the wind");
        assertEquals("Thank you! Enjoy the book.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(1, currentCustomer.getBorrowedBooksList().size());
        assertTrue(currentCustomer.getBorrowedBooksList().getFirst().getCheckOutStatus());
        assertEquals("Gone with the wind", currentCustomer.getBorrowedBooksList().getFirst().getName());

        LMS.processLibraryOperations("buy Gone with the wind");
        assertEquals("Your operation is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("return Hello Potter");
        assertEquals("That is not a valid book to return.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();


        LMS.processLibraryOperations("return Gone with the wind");
        assertEquals("Thank you for returning the book.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(0, currentCustomer.getBorrowedBooksList().size());

        LMS.processLibraryOperations(SYSTEM_OPTION_BACK);
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());
    }

    @Test
    public void testShowGoodByeMessageAfterQuit(){
        inStream = new ByteArrayInputStream("quit".getBytes());
        System.setIn(inStream);
        LMS.startSystem();
        assertEquals("Welcome to Biblioteca library management system.\n" +
                "Main Menu\n" +
                "List Books\n" +
                "List Movies\n" +
                "Please type in the operation you want to do: Thank you for using our system. Good bye!\n", outStream.toString());
    }
}
