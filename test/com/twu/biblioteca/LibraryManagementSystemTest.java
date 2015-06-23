package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static com.twu.biblioteca.PredefinedMovieDetails.*;
import static com.twu.biblioteca.PredefinedUserCredentials.CUSTOMER_ONE_LIBRARY_NUMBER;
import static com.twu.biblioteca.PredefinedUserCredentials.CUSTOMER_ONE_PASSWORD;
import static com.twu.biblioteca.SystemOptionConstants.SYSTEM_OPTION_BACK;
import static com.twu.biblioteca.UserSystemPositions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        LMS = new LibraryManagementSystem(itemsList);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        System.setIn(System.in);
    }

    @Test
    public void testShowAvailableBookList() {
        String simulatedUserLogin = CUSTOMER_ONE_LIBRARY_NUMBER + System.getProperty("line.separator")
                + CUSTOMER_ONE_PASSWORD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        outStream.reset();

        LMS.getCurrentCustomer().borrowItem(bookA);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.getCurrentCustomer().borrowItem(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.getCurrentCustomer().borrowItem(bookE);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.getCurrentCustomer().returnItem(bookC);
        LMS.showBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.getCurrentCustomer().returnItem(bookA);
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

        String simulatedUserLogin = CUSTOMER_ONE_LIBRARY_NUMBER + System.getProperty("line.separator")
                + CUSTOMER_ONE_PASSWORD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        outStream.reset();

        LMS.processLibraryOperations("borrow Harry Potter");
        assertEquals("That item is not available.\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("borrow Gone with the wind");
        assertEquals("Thank you! Enjoy it.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(1, LMS.getCurrentCustomer().getBorrowedItemsList().size());
        assertTrue(LMS.getCurrentCustomer().getBorrowedItemsList().getFirst().getCheckOutStatus());
        assertEquals("Gone with the wind", LMS.getCurrentCustomer().getBorrowedItemsList().getFirst().getName());

        LMS.processLibraryOperations("buy Gone with the wind");
        assertEquals("Your operation is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("return Hello Potter");
        assertEquals("That is not a valid item to return.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("return Gone with the wind");
        assertEquals("Thank you for returning it.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(0, LMS.getCurrentCustomer().getBorrowedItemsList().size());

        LMS.processLibraryOperations(SYSTEM_OPTION_BACK);
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());
    }

    @Test
    public void testProcessLibraryOperationsForMovies() {
        LMS.processMainMenuOperations("List Movies");
        assertEquals(SYSTEM_POSITION_LIST_MOVIES, LMS.getSystemCurrentPosition());
        outStream.reset();

        String simulatedUserLogin = CUSTOMER_ONE_LIBRARY_NUMBER + System.getProperty("line.separator")
                + CUSTOMER_ONE_PASSWORD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        outStream.reset();

        LMS.processLibraryOperations("borrow Star War");
        assertEquals("Thank you! Enjoy it.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(1, LMS.getCurrentCustomer().getBorrowedItemsList().size());
        assertTrue(LMS.getCurrentCustomer().getBorrowedItemsList().getFirst().getCheckOutStatus());
        assertEquals("Star War", LMS.getCurrentCustomer().getBorrowedItemsList().getFirst().getName());

        LMS.processLibraryOperations("buy Star War");
        assertEquals("Your operation is not available.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        LMS.processLibraryOperations("return Hello Potter");
        assertEquals("That is not a valid item to return.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();


        LMS.processLibraryOperations("return Star War");
        assertEquals("Thank you for returning it.\nPlease type in the operation you want to do: ", outStream.toString());
        outStream.reset();
        assertEquals(0, LMS.getCurrentCustomer().getBorrowedItemsList().size());

        LMS.processLibraryOperations(SYSTEM_OPTION_BACK);
        assertEquals(SYSTEM_POSITION_MAIN_MENU, LMS.getSystemCurrentPosition());
    }

    @Test
    public void testProcessLibraryOperationsForShowCustomerInfo() {
        String simulatedUserLogin = CUSTOMER_ONE_LIBRARY_NUMBER + System.getProperty("line.separator")
                + CUSTOMER_ONE_PASSWORD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        outStream.reset();

        LMS.processMainMenuOperations("Show Customer Information");
        assertEquals("Li Lei\n" +
                "harrisonsong1991@gmail.com\n" +
                "98826095\n" +
                "Please type in the operation you want to do: ", outStream.toString());
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

    @Test
    public void testCustomerLogin(){
        String simulatedUserLogin = "" + System.getProperty("line.separator")
                + CUSTOMER_ONE_PASSWORD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        assertEquals("Please Login First\n" +
                "library number: password: Your credential is incorrect.\n",outStream.toString());
        outStream.reset();

        simulatedUserLogin = CUSTOMER_ONE_LIBRARY_NUMBER + System.getProperty("line.separator")
                + "" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        assertEquals("Please Login First\n" +
                "library number: password: Your credential is incorrect.\n",outStream.toString());
        outStream.reset();

        simulatedUserLogin = CUSTOMER_ONE_LIBRARY_NUMBER + System.getProperty("line.separator")
                + CUSTOMER_ONE_PASSWORD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        assertEquals("Please Login First\n" +
                "library number: password: Login successfully! Hello Li Lei.\n",outStream.toString());
    }

    @Test
    public void testShowMainMenu(){
        LMS.showMainMenu();
        assertEquals("Main Menu\n" +
                "List Books\n" +
                "List Movies\n" +
                "Please type in the operation you want to do: ", outStream.toString());
        outStream.reset();

        String simulatedUserLogin = CUSTOMER_ONE_LIBRARY_NUMBER + System.getProperty("line.separator")
                + CUSTOMER_ONE_PASSWORD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserLogin.getBytes()));
        LMS.customerLogin();
        outStream.reset();
        LMS.showMainMenu();
        assertEquals("Main Menu\n" +
                "List Books\n" +
                "List Movies\n" +
                "Show Customer Information\n" +
                "Please type in the operation you want to do: ", outStream.toString());
    }
}
