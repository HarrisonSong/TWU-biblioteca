package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        assertEquals(SystemMessager.WELCOME_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowRemindingMessage(){
        SystemMessager.showRemindingMessage();
        assertEquals(SystemMessager.REMINDING_MESSAGE, outStream.toString());
    }

    @Test
    public void testShowInvalidMenuOptionMessage(){
        SystemMessager.showResponseMessage(SystemMessageType.INVALID_MENU_OPTION);
        assertEquals(SystemMessager.INVALID_MENU_OPTION_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowSuccessfulCheckOutMessage(){
        SystemMessager.showResponseMessage(SystemMessageType.SUCCESSFUL_CHECKOUT);
        assertEquals(SystemMessager.SUCCESSFUL_CHECKOUT_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowUnsuccessfulCheckOutMessage(){
        SystemMessager.showResponseMessage(SystemMessageType.UNSUCCESSFUL_CHECKOUT);
        assertEquals(SystemMessager.UNSUCCESSFUL_CHECKOUT_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowSuccessfulReturnMessage(){
        SystemMessager.showResponseMessage(SystemMessageType.SUCCESSFUL_RETURN);
        assertEquals(SystemMessager.SUCCESSFUL_RETURN_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowUnsuccessfulReturnMessage(){
        SystemMessager.showResponseMessage(SystemMessageType.UNSUCCESSFUL_RETURN);
        assertEquals(SystemMessager.UNSUCCESSFUL_RETURN_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowInvalidBookOptionMessage(){
        SystemMessager.showResponseMessage(SystemMessageType.INVALID_BOOK_OPTION);
        assertEquals(SystemMessager.INVALID_BOOK_OPTION_MESSAGE + "\n", outStream.toString());
    }
}
