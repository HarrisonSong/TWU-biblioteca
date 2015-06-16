package com.twu.biblioteca;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class Book {
    private String author;
    private int publishingYear;
    private boolean checkOutStatus;

    public Book(String author, int year) {
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

    public boolean getCheckOutStatus() {
        return this.checkOutStatus;
    }

    public void checkOut() {
        this.checkOutStatus = true;
    }

    public void checkIn() {
        this.checkOutStatus = false;
    }
}
