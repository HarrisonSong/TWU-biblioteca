package com.twu.biblioteca;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class Book extends LibraryItem {
    private String author;
    private int publishingYear;

    public Book(String book, String author, int year) {
        super(book);
        this.author = author;
        this.publishingYear = year;
        this.checkOutStatus = false;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublishingYear() {
        return this.publishingYear;
    }

    @Override
    public String toString(){
        return this.name + " " + this.author + " " + this.publishingYear;
    }
}
