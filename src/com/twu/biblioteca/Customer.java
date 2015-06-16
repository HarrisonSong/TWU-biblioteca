package com.twu.biblioteca;

import java.util.LinkedList;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class Customer {
    private String customerName;
    private LinkedList<Book> borrowedBooksList;

    public Customer(String name) {
       this(name, new LinkedList<Book>());
    }

    public Customer(String name, LinkedList<Book> booksList) {
        this.customerName = name;
        this.borrowedBooksList = booksList;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public LinkedList<Book> getBorrowedBooksList() {
        return this.borrowedBooksList;
    }

    public void borrowBook(Book newBook) {

    }

    public void returnBook(Book newBook) {

    }
}
