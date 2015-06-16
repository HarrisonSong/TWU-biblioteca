package com.twu.biblioteca;

import java.util.LinkedList;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystem {
    private Customer currentCustomer;
    private LinkedList<Book> booksList;
    private LinkedList<String> mainMenu;
    private String systemCurrentPosition;

    public LibraryManagementSystem(LinkedList<Book> books){
        this.booksList = books;
        currentCustomer = new Customer("Default Customer");

        // Initialize the menu list in the constructor first.
        // To be changed later.
        this.mainMenu = new LinkedList<String>();
        this.mainMenu.add("List Books");
        this.systemCurrentPosition = "main menu";
    }

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public String getSystemCurrentPosition() {
        return this.systemCurrentPosition;
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
        }else if(type.equals("invalid book option")){
            System.out.println("Your operation is not available.");
        }
    }

    public void processMainMenuOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        for(String option : this.mainMenu){
            if(option.equals("List Books") && option.toLowerCase().equals(operationContent)){
                showBooksList();
                this.systemCurrentPosition = "list books";
                return;
            }
        }
        showFlashMessage("invalid menu option");
    }

    public void processBookOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        if(operationContent.startsWith("borrow ")){
            String book = operationContent.replace("borrow ", "");
            Book targetBook = findBookIfAvailable(book);
            if(targetBook != null){
                currentCustomer.borrowBook(targetBook);
                showFlashMessage("successful checkout");
            }else {
                showFlashMessage("unsuccessful checkout");
            }
        }else if(operationContent.startsWith("return ")){
            String book = operationContent.replace("return ", "");
            Book targetBook = currentCustomer.findBookIfAvailableToReturn(book);
            if(targetBook != null){
                currentCustomer.returnBook(targetBook);
                showFlashMessage("successful return");
            }else{
                showFlashMessage("unsuccessful return");
            }
        }else if(operationContent.equals("back")){
            this.systemCurrentPosition = "main menu";
            this.showMainMenu();
        }else{
            showFlashMessage("invalid book option");
        }
    }

    public Book findBookIfAvailable(String bookName) {
        for (Book book : this.booksList) {
            if (!book.getCheckOutStatus() && book.getBookName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    private void showRemindingMessage() {
        System.out.print("Please type in the operation you want to do: ");
    }
}
