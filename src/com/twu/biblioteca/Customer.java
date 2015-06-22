package com.twu.biblioteca;

import java.util.LinkedList;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class Customer {
    private String customerName;
    private String email;
    private String phoneNumber;
    private LinkedList<LibraryItem> borrowedItemsList;
    private boolean isLoggedIn;

    public Customer(String name, String email, String phoneNumber) {
       this(name, email, phoneNumber,  new LinkedList<LibraryItem>());
    }

    public Customer(String name, String email, String phoneNumber, LinkedList<LibraryItem> booksList) {
        this.customerName = name;
        this.borrowedItemsList = booksList;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isLoggedIn = false;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void login(){
        isLoggedIn = true;
    }

    public LinkedList<LibraryItem> getBorrowedBooksList() {
        return this.borrowedItemsList;
    }

    public void borrowItem(LibraryItem newItem) {
        if(!this.borrowedItemsList.contains(newItem)){
            this.borrowedItemsList.addLast(newItem);
            newItem.checkOut();
        }
    }

    public void returnItem(LibraryItem oldItem) {
        if(this.borrowedItemsList.contains(oldItem)){
            this.borrowedItemsList.remove(oldItem);
            oldItem.checkIn();
        }
    }

    public LibraryItem findBookIfAvailableToReturn(String bookName){
        for(LibraryItem book : this.borrowedItemsList){
            if(book.getName().toLowerCase().equals(bookName.toLowerCase())){
                return book;
            }
        }
        return null;
    }
}
