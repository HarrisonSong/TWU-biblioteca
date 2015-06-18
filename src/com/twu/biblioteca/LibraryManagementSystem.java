package com.twu.biblioteca;

import java.util.LinkedList;
import java.util.Scanner;

import static com.twu.biblioteca.SystemOptionConstants.*;
import static com.twu.biblioteca.PredefinedUserDetails.*;
import static com.twu.biblioteca.MainMenuOptionConstants.*;
import static com.twu.biblioteca.UserSystemPositions.*;
import static com.twu.biblioteca.SystemMessageContants.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystem {
    private Customer currentCustomer;
    private LinkedList<Book> booksList;
    private MainMenu mainMenu;
    private String systemCurrentPosition;

    public LibraryManagementSystem(LinkedList<Book> books, LinkedList<String> menuList){
        this.booksList = books;
        currentCustomer = new Customer(DEFAULT_USERNAME);
        this.mainMenu = new MainMenu(menuList);
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
        this.mainMenu.showMainMenu();
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

    public void processMainMenuOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        for(String option : this.mainMenu.getMenuList()){
            if(option.equals(MAIN_MENU_LIST_BOOKS_OPTION) && option.toLowerCase().equals(operationContent)){
                showBooksList();
                this.systemCurrentPosition = SYSTEM_POSITION_LIST_BOOKS;
                return;
            }
        }
        SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.INVALID_MENU_OPTION);
    }

    public void processBookOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        if(operationContent.startsWith("borrow ")){
            String book = operationContent.replace("borrow ", "");
            Book targetBook = findBookIfAvailable(book);
            if(targetBook != null){
                currentCustomer.borrowBook(targetBook);
                SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.SUCCESSFUL_CHECKOUT);
            }else {
                SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.UNSUCCESSFUL_CHECKOUT);
            }
            showRemindingMessage();
        }else if(operationContent.startsWith("return ")){
            String book = operationContent.replace("return ", "");
            Book targetBook = currentCustomer.findBookIfAvailableToReturn(book);
            if(targetBook != null){
                currentCustomer.returnBook(targetBook);
                SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.SUCCESSFUL_RETURN);
            }else{
                SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.UNSUCCESSFUL_RETURN);
            }
            showRemindingMessage();
        }else if(operationContent.equals(SYSTEM_OPTION_BACK)){
            this.systemCurrentPosition = SYSTEM_POSITION_MAIN_MENU;
            this.showMainMenu();
        }else{
            SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION);
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
