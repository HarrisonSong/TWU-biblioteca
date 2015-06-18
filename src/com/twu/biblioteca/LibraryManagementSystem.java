package com.twu.biblioteca;

import java.util.LinkedList;
import java.util.Scanner;

import static com.twu.biblioteca.SystemOptions.*;
import static com.twu.biblioteca.PredefinedUserDetails.*;
import static com.twu.biblioteca.MainMenuOptions.*;
import static com.twu.biblioteca.SystemMessageTypes.*;
import static com.twu.biblioteca.UserSystemPositions.*;
import static com.twu.biblioteca.SystemMessages.*;

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
        currentCustomer = new Customer(DEFAULT_USERNAME);

        // Initialize the menu list in the constructor first.
        // To be changed later.
        this.mainMenu = new LinkedList<String>();
        this.mainMenu.add(MAIN_MENU_LIST_BOOKS_OPTION);
        this.systemCurrentPosition = SYSTEM_POSITION_MAIN_MENU;
    }

    public void startSystem(){
        showWelcomeMessage();
        showMainMenu();
        Scanner sc = new Scanner(System.in);
        String operation = sc.nextLine().trim().toLowerCase();
        while(!operation.equals(SYSTEM_OPTION_QUIT)){
            if(systemCurrentPosition.equals(SYSTEM_POSITION_MAIN_MENU)){
                processMainMenuOperations(operation);
            }else if(systemCurrentPosition.equals(SYSTEM_POSITION_LIST_BOOKS)){
                processBookOperations(operation);
            }
            operation = sc.nextLine();
        }
    }

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public String getSystemCurrentPosition() {
        return this.systemCurrentPosition;
    }

    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
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
        if(type.equals(INVALID_MENU_OPTION_MESSAGE_TYPE)){
            System.out.println(INVALID_MENU_OPTION_MESSAGE);
        }else if(type.equals(SUCCESSFUL_CHECKOUT_MESSAGE_TYPE)){
            System.out.println(SUCCESSFUL_CHECKOUT_MESSAGE);
        }else if(type.equals(UNSUCCESSFUL_CHECKOUT_MESSAGE_TYPE)){
            System.out.println(UNSUCCESSFUL_CHECKOUT_MESSAGE);
        }else if(type.equals(SUCCESSFUL_RETURN_MESSAGE_TYPE)){
            System.out.println(SUCCESSFUL_RETURN_MESSAGE);
        }else if(type.equals(UNSUCCESSFUL_RETURN_MESSAGE_TYPE)){
            System.out.println(UNSUCCESSFUL_RETURN_MESSAGE);
        }else if(type.equals(INVALID_BOOK_OPTION_MESSAGE_TYPE)){
            System.out.println(INVALID_BOOK_OPTION_MESSAGE);
        }
    }

    public void processMainMenuOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        for(String option : this.mainMenu){
            if(option.equals(MAIN_MENU_LIST_BOOKS_OPTION) && option.toLowerCase().equals(operationContent)){
                showBooksList();
                this.systemCurrentPosition = SYSTEM_POSITION_LIST_BOOKS;
                return;
            }
        }
        showFlashMessage(INVALID_MENU_OPTION_MESSAGE_TYPE);
    }

    public void processBookOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        if(operationContent.startsWith("borrow ")){
            String book = operationContent.replace("borrow ", "");
            Book targetBook = findBookIfAvailable(book);
            if(targetBook != null){
                currentCustomer.borrowBook(targetBook);
                showFlashMessage(SUCCESSFUL_CHECKOUT_MESSAGE_TYPE);
            }else {
                showFlashMessage(UNSUCCESSFUL_CHECKOUT_MESSAGE_TYPE);
            }
            showRemindingMessage();
        }else if(operationContent.startsWith("return ")){
            String book = operationContent.replace("return ", "");
            Book targetBook = currentCustomer.findBookIfAvailableToReturn(book);
            if(targetBook != null){
                currentCustomer.returnBook(targetBook);
                showFlashMessage(SUCCESSFUL_RETURN_MESSAGE_TYPE);
            }else{
                showFlashMessage(UNSUCCESSFUL_RETURN_MESSAGE_TYPE);
            }
            showRemindingMessage();
        }else if(operationContent.equals(SYSTEM_OPTION_BACK)){
            this.systemCurrentPosition = SYSTEM_POSITION_MAIN_MENU;
            this.showMainMenu();
        }else{
            showFlashMessage(INVALID_BOOK_OPTION_MESSAGE_TYPE);
            showRemindingMessage();
        }
    }

    public Book findBookIfAvailable(String bookName) {
        for (Book book : this.booksList) {
            if (!book.getCheckOutStatus() && book.getBookName().toLowerCase().equals(bookName)) {
                return book;
            }
        }
        return null;
    }

    private void showRemindingMessage() {
        System.out.print(REMINDING_MESSAGE);
    }
}
