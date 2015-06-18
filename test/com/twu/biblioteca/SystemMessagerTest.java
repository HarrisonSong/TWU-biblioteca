package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.SystemMessageTypeContants.*;
import static com.twu.biblioteca.SystemMessageTypeContants.INVALID_BOOK_OPTION_MESSAGE_TYPE;
import static com.twu.biblioteca.SystemMessageTypeContants.UNSUCCESSFUL_RETURN_MESSAGE_TYPE;
import static com.twu.biblioteca.SystemMessageContants.*;
import static com.twu.biblioteca.SystemMessageContants.INVALID_BOOK_OPTION_MESSAGE;
import static com.twu.biblioteca.SystemMessageContants.UNSUCCESSFUL_RETURN_MESSAGE;
import static org.junit.Assert.assertEquals;

/**
 * Created by qiyuesong on 18/6/15.
 */
public class SystemMessagerTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void testShowWelcomeMessage(){
        SystemMessager.showWelcomeMessage();
        assertEquals(WELCOME_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowRemindingMessage(){
        SystemMessager.showRemindingMessage();
        assertEquals(REMINDING_MESSAGE, outStream.toString());
    }

    @Test
    public void testShowInvalidMenuOptionMessage(){
        SystemMessager.showResponseMessage(INVALID_MENU_OPTION_MESSAGE_TYPE);
        assertEquals(INVALID_MENU_OPTION_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowSuccessfulCheckOutMessage(){
        SystemMessager.showResponseMessage(SUCCESSFUL_CHECKOUT_MESSAGE_TYPE);
        assertEquals(SUCCESSFUL_CHECKOUT_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowUnsuccessfulCheckOutMessage(){
        SystemMessager.showResponseMessage(UNSUCCESSFUL_CHECKOUT_MESSAGE_TYPE);
        assertEquals(UNSUCCESSFUL_CHECKOUT_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowSuccessfulReturnMessage(){
        SystemMessager.showResponseMessage(SUCCESSFUL_RETURN_MESSAGE_TYPE);
        assertEquals(SUCCESSFUL_RETURN_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowUnsuccessfulReturnMessage(){
        SystemMessager.showResponseMessage(UNSUCCESSFUL_RETURN_MESSAGE_TYPE);
        assertEquals(UNSUCCESSFUL_RETURN_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowInvalidBookOptionMessage(){
        SystemMessager.showResponseMessage(INVALID_BOOK_OPTION_MESSAGE_TYPE);
        assertEquals(INVALID_BOOK_OPTION_MESSAGE + "\n", outStream.toString());
    }
}
