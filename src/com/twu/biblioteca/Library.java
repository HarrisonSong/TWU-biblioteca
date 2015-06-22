package com.twu.biblioteca;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class Library {
    private LinkedList<LibraryItem> itemsList;

    public Library(LinkedList<LibraryItem> itemsList){
        this.itemsList = itemsList;
    }

    public LinkedList<LibraryItem> getItemsList() {
        return this.itemsList;
    }

    public LinkedList<LibraryItem> getAvailableItemsList(){
        LinkedList<LibraryItem> availableItemsList = new LinkedList<LibraryItem>();
        Iterator<LibraryItem> iterator = this.itemsList.iterator();
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            if(!currentItem.getCheckOutStatus()){
                availableItemsList.addLast(currentItem);
            }
        }
        return availableItemsList;
    }

    public LinkedList<LibraryItem> getAllBooksList(){
        LinkedList<LibraryItem> booksList = new LinkedList<LibraryItem>();
        Iterator<LibraryItem> iterator = this.itemsList.iterator();
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            if(currentItem instanceof Book){
                booksList.addLast(currentItem);
            }
        }
        return booksList;
    }

    public LinkedList<LibraryItem> getAvailableBooksList(){
        LinkedList<LibraryItem> availableBooksList = new LinkedList<LibraryItem>();
        Iterator<LibraryItem> iterator = this.itemsList.iterator();
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            if(!currentItem.getCheckOutStatus() && currentItem instanceof Book){
                availableBooksList.addLast(currentItem);
            }
        }
        return availableBooksList;
    }

    public LinkedList<LibraryItem> getAllMoviesList(){
        LinkedList<LibraryItem> moviesList = new LinkedList<LibraryItem>();
        Iterator<LibraryItem> iterator = this.itemsList.iterator();
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            if(currentItem instanceof Movie){
                moviesList.addLast(currentItem);
            }
        }
        return moviesList;
    }

    public LinkedList<LibraryItem> getAvailableMoviesList(){
        LinkedList<LibraryItem> availableMoviesList = new LinkedList<LibraryItem>();
        Iterator<LibraryItem> iterator = this.itemsList.iterator();
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            if(!currentItem.getCheckOutStatus() && currentItem instanceof Movie){
                availableMoviesList.addLast(currentItem);
            }
        }
        return availableMoviesList;
    }

    public void showAvailableBooksList(){
        LinkedList<LibraryItem> availableBooksList = getAvailableBooksList();
        Iterator<LibraryItem> iterator = availableBooksList.iterator();
        String resultString = "";
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            resultString = resultString + currentItem.toString() + "\n";
        }
        System.out.print(resultString);
    }

    public void showAvailableMoviesList(){
        LinkedList<LibraryItem> availableMoviesList = getAvailableMoviesList();
        Iterator<LibraryItem> iterator = availableMoviesList.iterator();
        String resultString = "";
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            resultString = resultString + currentItem.toString() + "\n";
        }
        System.out.print(resultString);
    }

    public LibraryItem finditem(String name){
        for (LibraryItem item : this.itemsList) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public LibraryItem findItemIfAvailable(String name) {
        for (LibraryItem item : this.itemsList) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public SystemMessageType processBooksOperations(String operation, Customer customer){
        SystemMessageType resultMessageType = SystemMessageType.INVALID_BOOK_OPTION;
        LibraryItemOperation libraryItemOperation = LibraryOperationParser.parseOperation(operation);
        switch (libraryItemOperation.getOperation()){
            case LIBRARY_OPTION_BORROW:{
                LibraryItem targetItem = this.findItemIfAvailable(libraryItemOperation.getTarget());
                if(targetItem != null){
                    customer.borrowBook((Book)targetItem);
                    resultMessageType = SystemMessageType.SUCCESSFUL_CHECKOUT;
                }else {
                    resultMessageType = SystemMessageType.UNSUCCESSFUL_CHECKOUT;
                }
                break;
            }
            case LIBRARY_OPTION_RETURN:{
                LibraryItem targetItem = customer.findBookIfAvailableToReturn(libraryItemOperation.getTarget());
                if(targetItem != null) {
                    customer.returnBook((Book)targetItem);
                    resultMessageType = SystemMessageType.SUCCESSFUL_RETURN;
                }else {
                    resultMessageType = SystemMessageType.UNSUCCESSFUL_RETURN;
                }
                break;
            }
            case LIBRARY_OPTION_UNKNOWN:
                break;
            default:
                break;
        }
        return resultMessageType;
    }
}
