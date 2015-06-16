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
        if(type.equals("invalid menu option")){
            System.out.println("Select a valid option!");
        }else if(type.equals("successful checkout")){
            System.out.println("Thank you! Enjoy the book.");
        }else if(type.equals("unsuccessful checkout")){
            System.out.println("That book is not available.");
        }else if(type.equals("successful return")){
            System.out.println("Thank you for returning the book.");
        }else if(type.equals("unsuccessful return")){
            System.out.println("That is not a valid book to return.");
            //<editor-fold desc="Description">
        }
        //</editor-fold>
    }

    public void processMainMenuOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        for(String option : this.mainMenu){
            if(option.equals("List Books") && option.toLowerCase().equals(operationContent)){
                showBooksList();
                return;
            }
        }
        showFlashMessage("invalid menu option");
    }

    private void showRemindingMessage() {
        System.out.print("Please type in the operation you want to do: ");
    }
}
