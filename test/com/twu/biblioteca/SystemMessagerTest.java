package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.SystemMessageContants.*;
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
        SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.INVALID_MENU_OPTION);
        assertEquals(INVALID_MENU_OPTION_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowSuccessfulCheckOutMessage(){
        SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.SUCCESSFUL_CHECKOUT);
        assertEquals(SUCCESSFUL_CHECKOUT_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowUnsuccessfulCheckOutMessage(){
        SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.UNSUCCESSFUL_CHECKOUT);
        assertEquals(UNSUCCESSFUL_CHECKOUT_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowSuccessfulReturnMessage(){
        SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.SUCCESSFUL_RETURN);
        assertEquals(SUCCESSFUL_RETURN_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowUnsuccessfulReturnMessage(){
        SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.UNSUCCESSFUL_RETURN);
        assertEquals(UNSUCCESSFUL_RETURN_MESSAGE + "\n", outStream.toString());
    }

    @Test
    public void testShowInvalidBookOptionMessage(){
        SystemMessager.showResponseMessage(SystemMessager.SystemMessageType.INVALID_BOOK_OPTION);
        assertEquals(INVALID_BOOK_OPTION_MESSAGE + "\n", outStream.toString());
    }
}
