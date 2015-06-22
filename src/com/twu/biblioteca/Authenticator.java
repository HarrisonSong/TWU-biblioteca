package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.twu.biblioteca.PredefinedUserCredentials.*;
import static com.twu.biblioteca.PredefinedUserDetails.*;

/**
 * Created by qiyuesong on 22/6/15.
 */
public class Authenticator {
    private HashMap<Customer, Credential> customerCredentials;

    public Authenticator(){
        // Load all predefined customer credentials.
        this.customerCredentials = new HashMap<Customer, Credential>();
        this.customerCredentials.put(new Customer(CUSTOMER_ONE_NAME, CUSTOMER_ONE_EMAIL, CUSTOMER_ONE_PHONENUMBER), new Credential(CUSTOMER_ONE_LIBRARY_NUMBER, CUSTOMER_ONE_PASSWORD));
        this.customerCredentials.put(new Customer(CUSTOMER_TWO_NAME, CUSTOMER_TWO_EMAIL, CUSTOMER_TWO_PHONENUMBER), new Credential(CUSTOMER_TWO_LIBRARY_NUMBER, CUSTOMER_TWO_PASSWORD));
    }

    public Authenticator(HashMap<Customer, Credential> customerCredentials){
        this.customerCredentials = customerCredentials;
    }

    public Customer authenticate(String number, String password){
        this.customerCredentials.values();
        Iterator iterator = this.customerCredentials.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Customer, Credential> pair = (Map.Entry<Customer, Credential>)iterator.next();
            if(pair.getValue().getLibraryNumber().equals(number) && pair.getValue().getPassword().equals(password)){
                Customer currentCustomer = pair.getKey();
                currentCustomer.login();
                return currentCustomer;
            }
        }
        return null;
    }
}
