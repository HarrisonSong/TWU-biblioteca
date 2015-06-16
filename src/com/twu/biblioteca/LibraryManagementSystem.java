package com.twu.biblioteca;

import java.util.LinkedList;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystem {
    private Customer currentCustomer;
    private LinkedList<Book> booksList;

    public LibraryManagementSystem(LinkedList<Book> books){
        this.booksList = books;
        currentCustomer = new Customer("Default User");
    }

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public LinkedList<Book> getBooksList() {
        return this.booksList;
    }

    public void showWelcomeMessage() {

    }

    public void showMainMenu() {

    }

    public void showBooksList() {

    }
}
