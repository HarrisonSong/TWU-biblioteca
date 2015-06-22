package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.twu.biblioteca.PredefinedUserCredentials.*;

/**
 * Created by qiyuesong on 22/6/15.
 */
public class AuthenticatorTest {
    private Authenticator authenticator;

    @Before
    public void setup(){
        authenticator = new Authenticator();
    }

    @Test
    public void testAuthenticateBy123_4567AndSongQiyue(){
        assertNull(authenticator.authenticate("123-4567", "Song Qiyue"));
    }

    @Test
    public void testAuthenticateBySongQiyue(){
        assertNull(authenticator.authenticate("", "Song Qiyue"));
    }

    @Test
    public void testAuthenticateBy123_4567(){
        assertNull(authenticator.authenticate("123-4567", ""));
    }

    @Test
    public void testAuthenticateBy142_3421And$Harrison0610(){
        Customer loggedInCustomer = authenticator.authenticate(CUSTOMER_ONE_LIBRARY_NUMBER, CUSTOMER_ONE_PASSWORD);
        assertNotNull(loggedInCustomer);
        assertTrue(loggedInCustomer.isLoggedIn());
    }

    @Test
    public void testAuthenticateBy429_6950And$G38110004(){
        Customer loggedInCustomer = authenticator.authenticate(CUSTOMER_TWO_LIBRARY_NUMBER, CUSTOMER_TWO_PASSWORD);
        assertNotNull(loggedInCustomer);
        assertTrue(loggedInCustomer.isLoggedIn());
    }
}
