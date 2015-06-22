package com.twu.biblioteca;

/**
 * Created by qiyuesong on 22/6/15.
 */
public class Credential {
    private String libraryNumber;
    private String password;

    public Credential(String number, String password){
        this.libraryNumber = number;
        this.password = password;
    }

    public String getLibraryNumber(){
        return this.libraryNumber;
    }

    public String getPassword(){
        return this.password;
    }
}
