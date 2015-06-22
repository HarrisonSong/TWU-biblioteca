package com.twu.biblioteca;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class Library {
    private LinkedList<LibraryItem> booksList;

    public Library(LinkedList<LibraryItem> booksList){
        this.booksList = booksList;
    }

    public LinkedList<LibraryItem> getBooksList() {
        return this.booksList;
    }

    public LinkedList<LibraryItem> getAvailableBooksList(){
        LinkedList<LibraryItem> availableBooksList = new LinkedList<LibraryItem>();
        Iterator<LibraryItem> iterator = this.booksList.iterator();
        while(iterator.hasNext()){
            LibraryItem currentItem = iterator.next();
            if(!currentItem.getCheckOutStatus()){
                availableBooksList.addLast(currentItem);
            }
        }
        return availableBooksList;
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

    public LibraryItem findBook(String bookName){
        for (LibraryItem item : this.getBooksList()) {
            if (item.getName().toLowerCase().equals(bookName.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public LibraryItem findBookIfAvailable(String bookName) {
        for (LibraryItem item : this.getAvailableBooksList()) {
            if (item.getName().toLowerCase().equals(bookName.toLowerCase())) {
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
                LibraryItem targetItem = this.findBookIfAvailable(libraryItemOperation.getTarget());
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
