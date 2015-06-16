package com.twu.biblioteca;

import java.util.LinkedList;

public class BibliotecaApp {
    private static LibraryManagementSystem LMS;

    public static void main(String[] args) {
        setup();
        LMS.startSystem();
    }

    private static void setup() {
        LinkedList<Book> booksList = new LinkedList<Book>();
        booksList.add(new Book("One Hundred Years of Solitude", "Gabriel García Márquez", 1910));
        booksList.add(new Book("The Old Man and the Sea", "Ernest Hemingway", 1990));
        booksList.add(new Book("Programming Pearl", "Jon Bentley", 2003));
        booksList.add(new Book("Hamlet", "Shakespeare", 1972));
        booksList.add(new Book("gone with the wind", "Margaret Mitchell", 1980));
        LMS = new LibraryManagementSystem(booksList);
    }
}
