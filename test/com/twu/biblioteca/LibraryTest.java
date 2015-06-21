package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static org.junit.Assert.*;

/**
 * Created by qiyuesong on 18/6/15.
 */
public class LibraryTest {
    private Library library;
    private  Book bookA, bookB, bookC, bookD, bookE;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Before
    public void setup(){
        System.setOut(new PrintStream(outStream));

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

        library = new Library(booksList);
    }

    @After
    public void cleanUp(){
        System.setOut(null);
    }

    @Test
    public void testGetAllBooksList(){
        assertEquals(5, library.getBooksList().size());
    }

    @Test
    public void testGetAvailableBooksListAfterBookAHasBeenCheckedOut(){
        assertEquals(5, library.getAvailableBooksList().size());
        bookA.checkOut();
        assertEquals(4, library.getAvailableBooksList().size());
    }

    @Test
    public void testGetAvailableBooksListAfterBookAHasBeenCheckedIn(){
        bookA.checkOut();
        assertEquals(4, library.getAvailableBooksList().size());
        bookA.checkIn();
        assertEquals(5, library.getAvailableBooksList().size());
    }

    @Test
    public void testGetAvailableBooksListAfterBookCHasBeenCheckedOut(){
        assertEquals(5, library.getAvailableBooksList().size());
        bookC.checkOut();
        assertEquals(4, library.getAvailableBooksList().size());
    }

    @Test
    public void testGetAvailableBooksListAfterBookCHasBeenCheckedIn(){
        bookC.checkOut();
        assertEquals(4, library.getAvailableBooksList().size());
        bookC.checkIn();
        assertEquals(5, library.getAvailableBooksList().size());
    }

    @Test
    public void testShowAvailableBooksList(){
        bookA.checkOut();
        library.showAvailableBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Hamlet Shakespeare 1972\n" +
                "Gone with the wind Margaret Mitchell 1980\n", outStream.toString());
        outStream.reset();

        bookD.checkOut();
        library.showAvailableBooksList();
        assertEquals("The Old Man and the Sea Ernest Hemingway 1990\n" +
                "Programming Pearl Jon Bentley 2003\n" +
                "Gone with the wind Margaret Mitchell 1980\n", outStream.toString());
    }

    @Test
    public void testBookAIsAvailable(){
        assertNotNull(library.findBookIfAvailable(BOOK_ONE_NAME));
        assertTrue(library.findBookIfAvailable(BOOK_ONE_NAME) instanceof Book);
    }

    @Test
    public void testBookAIsNotAvailableAfterBeingCheckedOut(){
        bookA.checkOut();
        assertNull(library.findBookIfAvailable(BOOK_ONE_NAME));
    }

    @Test
    public void testBuyBookA(){
        assertEquals(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION, library.processBooksOperations("sell One Hundred Years of Solitude"));
        assertFalse(bookA.getCheckOutStatus());
    }

    @Test
    public void testSellBookA(){
        assertEquals(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION, library.processBooksOperations("buy One Hundred Years of Solitude"));
        assertFalse(bookA.getCheckOutStatus());
    }

    @Test
    public void testBorrowBookA(){
        assertEquals(SystemMessager.SystemMessageType.SUCCESSFUL_CHECKOUT, library.processBooksOperations("borrow One Hundred Years of Solitude"));
        assertTrue(bookA.getCheckOutStatus());
    }

    @Test void testBorrowUnknownBook(){
        bookA.checkOut();
        assertEquals(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION, library.processBooksOperations("borrow One"));
    }

    @Test void testSellBookBAfterBorrowing(){
        bookB.checkOut();
        assertEquals(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION, library.processBooksOperations("sell The Old Man and the Sea"));
        assertTrue(bookB.getCheckOutStatus());
    }

    @Test void testBuyBookBAfterBorrowing(){
        bookB.checkOut();
        assertEquals(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION, library.processBooksOperations("buy The Old Man and the Sea"));
        assertTrue(bookB.getCheckOutStatus());
    }

    @Test void testReturnBookBAfterBorrowing(){
        bookB.checkOut();
        assertEquals(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION, library.processBooksOperations("return The Old Man and the Sea"));
        assertFalse(bookB.getCheckOutStatus());
    }

    @Test void testReturnUnknownBookAftrerBorrowingBookB(){
        bookB.checkOut();
        assertEquals(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION, library.processBooksOperations("return Galaxy War"));
    }
}
