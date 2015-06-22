package com.twu.biblioteca;

import java.util.LinkedList;
import java.util.Scanner;

import static com.twu.biblioteca.MainMenuOptionConstants.*;
import static com.twu.biblioteca.PredefinedUserDetails.*;
import static com.twu.biblioteca.SystemOptionConstants.*;
import static com.twu.biblioteca.UserSystemPositions.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystem {
    private Customer currentCustomer;
    private MainMenu mainMenu;
    private Library library;
    private String systemCurrentPosition;

    public LibraryManagementSystem(LinkedList<LibraryItem> items, LinkedList<String> menuList){
        this.library = new Library(items);
        currentCustomer = new Customer(DEFAULT_USERNAME);
        this.mainMenu = new MainMenu(menuList);
        this.systemCurrentPosition = SYSTEM_POSITION_MAIN_MENU;
    }

    public void startSystem(){
        SystemMessager.showWelcomeMessage();
        showMainMenu();
        Scanner sc = new Scanner(System.in);
        String operation = sc.nextLine().trim().toLowerCase();
        while(!operation.equals(SYSTEM_OPTION_QUIT)){
            if(systemCurrentPosition.equals(SYSTEM_POSITION_MAIN_MENU)){
                processMainMenuOperations(operation);
            }else if(systemCurrentPosition.equals(SYSTEM_POSITION_LIST_BOOKS)){
                processLibraryOperations(operation);
            }
            operation = sc.nextLine();
        }
        SystemMessager.showGoodByeMessage();
    }

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public String getSystemCurrentPosition() {
        return this.systemCurrentPosition;
    }

    public void showMainMenu() {
        this.mainMenu.showMainMenu();
        SystemMessager.showRemindingMessage();
    }

    public void showBooksList() {
        this.library.showAvailableBooksList();
        SystemMessager.showRemindingMessage();
    }

    public void showMoviesList() {
        this.library.showAvailableMoviesList();
        SystemMessager.showRemindingMessage();
    }

    public void processMainMenuOperations(String operation){
        String detectedOperation = this.mainMenu.checkOperation(operation);
        if(detectedOperation.equals(MAIN_MENU_LIST_BOOKS_OPTION)){
            showBooksList();
            this.systemCurrentPosition = SYSTEM_POSITION_LIST_BOOKS;
        }else if(detectedOperation.equals(MAIN_MENU_LIST_MOVIES_OPTION)){
            showMoviesList();
            this.systemCurrentPosition = SYSTEM_POSITION_LIST_MOVIES;
        }else{
            SystemMessager.showResponseMessage(SystemMessageType.INVALID_MENU_OPTION);
        }
    }

    public void processLibraryOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        if(operationContent.equals(SYSTEM_OPTION_BACK)){
            this.systemCurrentPosition = SYSTEM_POSITION_MAIN_MENU;
            this.showMainMenu();
        }else{
            SystemMessager.showResponseMessage(this.library.processLibraryItemsOperations(operationContent, currentCustomer));
            SystemMessager.showRemindingMessage();
        }
    }
}
