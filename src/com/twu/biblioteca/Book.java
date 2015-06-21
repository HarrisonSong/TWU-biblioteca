package com.twu.biblioteca;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class Book {
    private String bookName;
    private String author;
    private int publishingYear;
    private boolean checkOutStatus;

    public Book(String book, String author, int year) {
        this.bookName = book;
        this.author = author;
        this.publishingYear = year;
        this.checkOutStatus = false;
    }

    public String getBookName() {
        return this.bookName;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublishingYear() {
        return this.publishingYear;
    }

    public boolean getCheckOutStatus() {
        return this.checkOutStatus;
    }

    public void checkOut() {
        this.checkOutStatus = true;
    }

    public void checkIn() {
        this.checkOutStatus = false;
    }

    @Override
    public String toString(){
        return getBookName() + " " + getAuthor() + " " + getPublishingYear();
    }
}
