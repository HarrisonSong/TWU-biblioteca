package com.twu.biblioteca;

import java.util.HashMap;

/**
 * Created by qiyuesong on 22/6/15.
 */
public class Authenticator {
    private HashMap<Customer, Credential> customerCredentials;

    public Authenticator(){

    }

    public Authenticator(HashMap<Customer, Credential> customerCredentials){
        this.customerCredentials = customerCredentials;
    }

    public Customer authenticate(String number, String password){
        return null;
    }
}
