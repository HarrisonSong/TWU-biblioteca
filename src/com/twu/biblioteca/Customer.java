package com.twu.biblioteca;

import java.util.LinkedList;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class Customer {
    private String customerName;
    private LinkedList<LibraryItem> borrowedItemsList;

    public Customer(String name) {
       this(name, new LinkedList<LibraryItem>());
    }

    public Customer(String name, LinkedList<LibraryItem> booksList) {
        this.customerName = name;
        this.borrowedItemsList = booksList;
    }

    public String getCustomerName() {
        return this.customerName;
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
