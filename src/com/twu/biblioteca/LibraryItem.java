package com.twu.biblioteca;

/**
 * Created by qiyuesong on 22/6/15.
 */
public class LibraryItem {
    protected String name;
    protected boolean checkOutStatus;

    public LibraryItem(String name) {
        this.name = name;
        this.checkOutStatus = false;
    }

    public String getName() {
        return this.name;
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
        return this.name;
    }
}
