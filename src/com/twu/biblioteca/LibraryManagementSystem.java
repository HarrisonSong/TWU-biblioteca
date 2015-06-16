package com.twu.biblioteca;

import java.util.LinkedList;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystem {
    private Customer currentCustomer;
    private LinkedList<Book> booksList;
    private LinkedList<String> mainMenu;

    public LibraryManagementSystem(LinkedList<Book> books){
        this.booksList = books;
        currentCustomer = new Customer("Default Customer");

        // Initialize the menu list in the constructor first.
        // To be changed later.
        this.mainMenu = new LinkedList<String>();
        this.mainMenu.add("List Books");
    }

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public LinkedList<Book> getBooksList() {
        return this.booksList;
    }

    public void showWelcomeMessage() {
        System.out.println("Welcome to biblioteca library management system.\nBelow is the operations you can do.");
    }

    public void showMainMenu() {
        for(String item : this.mainMenu){
            System.out.println(item);
        }
        showRemindingMessage();
    }

    public void showBooksList() {
        for(int bookIndex = 0; bookIndex < this.booksList.size(); bookIndex++ ){
            Book currentBook = this.booksList.get(bookIndex);
            if(!currentBook.getCheckOutStatus()){
                System.out.println(currentBook.getBookName() + " " + currentBook.getAuthor() + " " + currentBook.getPublishingYear());
            }
        }
        showRemindingMessage();
    }

    public void showFlashMessage(String type){

    }

    private void showRemindingMessage() {
        System.out.print("Please type in the operation you want to do: ");
    }
}
