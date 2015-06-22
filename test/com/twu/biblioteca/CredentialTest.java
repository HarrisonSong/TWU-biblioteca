package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.PredefinedUserCredentials.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by qiyuesong on 22/6/15.
 */
public class CredentialTest {
    private Credential credentialA, credentialB;

    @Before
    public void setup(){
        credentialA = new Credential(CUSTOMER_ONE_LIBRARY_NUMBER, CUSTOMER_ONE_PASSWORD);
        credentialB = new Credential(CUSTOMER_TWO_LIBRARY_NUMBER, CUSTOMER_TWO_PASSWORD);
    }

    @Test
    public void testGetCredentialALibraryNumber(){
        assertEquals(CUSTOMER_ONE_LIBRARY_NUMBER, credentialA.getLibraryNumber());
    }

    @Test
    public void testGetCredentialBLibraryNumber(){
        assertEquals(CUSTOMER_TWO_LIBRARY_NUMBER, credentialB.getLibraryNumber());
    }

    @Test
    public void testGetCredentialAPassword(){
        assertEquals(CUSTOMER_ONE_PASSWORD, credentialA.getPassword());

    }

    @Test
    public void testGetCredentialBPassword(){
        assertEquals(CUSTOMER_TWO_PASSWORD, credentialB.getPassword());
    }

}
