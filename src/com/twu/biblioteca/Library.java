package com.twu.biblioteca;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class Library {
    private LinkedList<Book> booksList;

    public Library(LinkedList<Book> booksList){
        this.booksList = booksList;
    }

    public LinkedList<Book> getBooksList() {
        return this.booksList;
    }

    public LinkedList<Book> getAvailableBooksList(){
        LinkedList<Book> availableBooksList = new LinkedList<Book>();
        Iterator<Book> iterator = this.booksList.iterator();
        while(iterator.hasNext()){
            Book currentBook = iterator.next();
            if(!currentBook.getCheckOutStatus()){
                availableBooksList.addLast(currentBook);
            }
        }
        return availableBooksList;
    }

    public void showAvailableBooksList(){
        LinkedList<Book> availableBooksList = getAvailableBooksList();
        Iterator<Book> iterator = availableBooksList.iterator();
        String resultString = "";
        while(iterator.hasNext()){
            Book currentBook = iterator.next();
            resultString = resultString + currentBook.toString() + "\n";
        }
        System.out.print(resultString);
    }

    public Book findBook(String bookName){
        for (Book book : this.getBooksList()) {
            if (book.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    public Book findBookIfAvailable(String bookName) {
        for (Book book : this.getAvailableBooksList()) {
            if (book.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    public SystemMessageType processBooksOperations(String operation, Customer customer){
        SystemMessageType resultMessageType = SystemMessageType.INVALID_BOOK_OPTION;
        if(operation.startsWith("borrow ")){
            String bookName = operation.replace("borrow ", "");
            Book targetBook = this.findBookIfAvailable(bookName);
            if(targetBook != null){
                customer.borrowBook(targetBook);
                resultMessageType = SystemMessageType.SUCCESSFUL_CHECKOUT;
            }else {
                resultMessageType = SystemMessageType.UNSUCCESSFUL_CHECKOUT;
            }
        }else if(operation.startsWith("return ")){
            String bookName = operation.replace("return ", "");
            Book targetBook = customer.findBookIfAvailableToReturn(bookName);
            if(targetBook != null) {
                customer.returnBook(targetBook);
                resultMessageType = SystemMessageType.SUCCESSFUL_RETURN;
            }else {
                resultMessageType = SystemMessageType.UNSUCCESSFUL_RETURN;
            }
        }
        return resultMessageType;
    }
}
