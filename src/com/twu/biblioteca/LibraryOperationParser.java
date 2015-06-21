package com.twu.biblioteca;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class LibraryOperationParser {

    public static BookOperation parseOperation(String operationString){
        return new BookOperation(LibraryOptionType.LIBRARY_OPTION_UNKNOWN, "");
    }
}
