package com.twu.biblioteca;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class LibraryItemOperation {
    private LibraryOptionType operation;
    private String target;

    public LibraryItemOperation(LibraryOptionType operation, String target){
        this.operation = operation;
        this.target = target;
    }

    public LibraryOptionType getOperation(){
        return this.operation;
    }

    public String getTarget(){
        return this.target;
    }
}
