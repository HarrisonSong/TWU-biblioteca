package com.twu.biblioteca;

import com.twu.biblioteca.model.Customer;
import com.twu.biblioteca.model.Library;

import java.util.LinkedList;
import java.util.Scanner;

import static com.twu.biblioteca.MainMenuOptionConstants.*;
import static com.twu.biblioteca.SystemOptionConstants.SYSTEM_OPTION_BACK;
import static com.twu.biblioteca.SystemOptionConstants.SYSTEM_OPTION_QUIT;
import static com.twu.biblioteca.UserSystemPositions.*;

/**
 * Created by qiyuesong on 16/6/15.
 */
public class LibraryManagementSystem {
    private Customer currentCustomer;
    private MainMenu mainMenu;
    private Library library;
    private String systemCurrentPosition;
    private Authenticator authenticator;

    public LibraryManagementSystem(LinkedList<LibraryItem> items){
        this.library = new Library(items);
        this.mainMenu = new MainMenu();
        this.systemCurrentPosition = SYSTEM_POSITION_MAIN_MENU;
        this.authenticator = new Authenticator();
    }

    public void startSystem(){
        SystemMessager.showWelcomeMessage();
        showMainMenu();
        Scanner sc = new Scanner(System.in);
        String operation = sc.nextLine().trim().toLowerCase();
        while(!operation.equals(SYSTEM_OPTION_QUIT)){
            if(systemCurrentPosition.equals(SYSTEM_POSITION_MAIN_MENU)){
                processMainMenuOperations(operation);
            }else if(systemCurrentPosition.equals(SYSTEM_POSITION_LIST_BOOKS) || systemCurrentPosition.equals(SYSTEM_POSITION_LIST_MOVIES)){
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
        if(currentCustomer == null){
            this.mainMenu.showMainMenu(false);
        }else{
            this.mainMenu.showMainMenu(true);
        }
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

    public void showCustomerInformation(){
        System.out.println(currentCustomer.toString());
        SystemMessager.showRemindingMessage();
    }

    public void processMainMenuOperations(String operation){
        String detectedOperation = this.mainMenu.checkOperation(operation);
        if(detectedOperation.equals(MAIN_MENU_LIST_BOOKS_OPTION)){
            showBooksList();
            this.systemCurrentPosition = SYSTEM_POSITION_LIST_BOOKS;
        }else if(detectedOperation.equals(MAIN_MENU_LIST_MOVIES_OPTION)) {
            showMoviesList();
            this.systemCurrentPosition = SYSTEM_POSITION_LIST_MOVIES;
        }else if(detectedOperation.equals(MAIN_MENU_SHOW_CUSTOMER_INFORMATION_OPTION) && currentCustomer != null){
            showCustomerInformation();
        }else{
            SystemMessager.showResponseMessage(SystemMessageType.INVALID_MENU_OPTION);
        }
    }

    public void processLibraryOperations(String operation){
        String operationContent = operation.trim().toLowerCase();
        if(operationContent.equals(SYSTEM_OPTION_BACK)){
            this.systemCurrentPosition = SYSTEM_POSITION_MAIN_MENU;
            this.showMainMenu();
        }else if(currentCustomer == null) {
            customerLogin();
            SystemMessager.showRemindingMessage();
        }else{
            SystemMessager.showResponseMessage(this.library.processLibraryItemsOperations(operationContent, currentCustomer));
            SystemMessager.showRemindingMessage();
        }
    }

    public void customerLogin(){
        System.out.println("Please Login First");
        System.out.print("library number: ");
        Scanner sc = new Scanner(System.in);
        String libraryNumber = sc.nextLine().trim();
        System.out.print("password: ");
        String password = sc.nextLine().trim();
        this.currentCustomer = this.authenticator.authenticate(libraryNumber, password);
        if(this.currentCustomer != null){
            System.out.println("Login successfully! Hello " + this.currentCustomer.getCustomerName() + ".");
        }else{
            System.out.println("Your credential is incorrect.");
        }
    }
}
