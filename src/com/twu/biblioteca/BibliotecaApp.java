package com.twu.biblioteca;

import java.util.LinkedList;

import static com.twu.biblioteca.PredefinedBooksDetails.*;
import static com.twu.biblioteca.PredefinedMovieDetails.*;

public class BibliotecaApp {
    private static LibraryManagementSystem LMS;

    public static void main(String[] args) {
        setup();
        LMS.startSystem();
    }

    private static void setup() {
        LinkedList<LibraryItem> itemsList = new LinkedList<LibraryItem>();
        itemsList.add(new Book(BOOK_ONE_NAME, BOOK_ONE_AUTHOR, BOOK_ONE_PUBLISHING_YEAR));
        itemsList.add(new Book(BOOK_TWO_NAME, BOOK_TWO_AUTHOR, BOOK_TWO_PUBLISHING_YEAR));
        itemsList.add(new Book(BOOK_THREE_NAME, BOOK_THREE_AUTHOR, BOOK_THREE_PUBLISHING_YEAR));
        itemsList.add(new Book(BOOK_FOUR_NAME, BOOK_FOUR_AUTHOR, BOOK_FOUR_PUBLISHING_YEAR));
        itemsList.add(new Book(BOOK_FIVE_NAME, BOOK_FIVE_AUTHOR, BOOK_FIVE_PUBLISHING_YEAR));
        itemsList.add(new Movie(MOVIE_ONE_NAME, MOVIE_ONE_YEAR, MOVIE_ONE_DIRECTOR, MOVIE_ONE_RATING));
        itemsList.add(new Movie(MOVIE_TWO_NAME, MOVIE_TWO_YEAR, MOVIE_TWO_DIRECTOR, MOVIE_TWO_RATING));
        itemsList.add(new Movie(MOVIE_THREE_NAME, MOVIE_THREE_YEAR, MOVIE_THREE_DIRECTOR, MOVIE_THREE_RATING));
        itemsList.add(new Movie(MOVIE_FOUR_NAME, MOVIE_FOUR_YEAR, MOVIE_FOUR_DIRECTOR, MOVIE_FOUR_RATING));

        LMS = new LibraryManagementSystem(itemsList);
    }
}
