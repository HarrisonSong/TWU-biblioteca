package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class BookOperationTest {
    private BookOperation operationA, operationB, operationC;

    @Before
    public void setup(){
        operationA = new BookOperation(LibraryOptionType.LIBRARY_OPTION_BORROW, "Galaxy War");
        operationB = new BookOperation(LibraryOptionType.LIBRARY_OPTION_RETURN, "Javascript Advanced");
        operationC = new BookOperation(LibraryOptionType.LIBRARY_OPTION_UNKNOWN, "Master Angular.js");
    }

    @Test
    public void testOperationAGetOperation(){
        assertEquals(LibraryOptionType.LIBRARY_OPTION_BORROW, operationA.getOperation());
    }

    @Test
    public void testOperationAGetTarget(){
        assertEquals("Galaxy War", operationA.getTarget());
    }

    @Test
    public void testOperationBGetOperation(){
        assertEquals(LibraryOptionType.LIBRARY_OPTION_RETURN, operationB.getOperation());
    }

    @Test
    public void testOperationBGetTarget(){
        assertEquals("Javascript Advanced", operationB.getTarget());
    }

    @Test
    public void testOperationCGetOperation(){
        assertEquals(LibraryOptionType.LIBRARY_OPTION_UNKNOWN, operationC.getOperation());
    }

    @Test
    public void testOperationCGetTarget(){
        assertEquals("Master Angular.js", operationC.getTarget());
    }
}
