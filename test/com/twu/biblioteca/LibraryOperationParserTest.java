package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by qiyuesong on 21/6/15.
 */
public class LibraryOperationParserTest {
    @Test
    public void testParsingBorrowBookHamlet(){
        BookOperation operation = LibraryOperationParser.parseOperation("Borrow Hamlet");
        assertEquals(LibraryOptionType.LIBRARY_OPTION_BORROW, operation.getOperation());
        assertEquals("hamlet", operation.getTarget());
    }

    @Test
    public void testParsingBorrowBookGoneWithTheWind(){
        BookOperation operation = LibraryOperationParser.parseOperation("Borrow Gone with the wind");
        assertEquals(LibraryOptionType.LIBRARY_OPTION_BORROW, operation.getOperation());
        assertEquals("gone with the wind", operation.getTarget());
    }

    @Test
    public void testParsingReturnBookHamlet(){
        BookOperation operation = LibraryOperationParser.parseOperation("return Hamlet");
        assertEquals(LibraryOptionType.LIBRARY_OPTION_RETURN, operation.getOperation());
        assertEquals("hamlet", operation.getTarget());
    }

    @Test
    public void testParsingReturnBookGoneWithTheWind(){
        BookOperation operation = LibraryOperationParser.parseOperation("return Gone with the wind");
        assertEquals(LibraryOptionType.LIBRARY_OPTION_RETURN, operation.getOperation());
        assertEquals("gone with the wind", operation.getTarget());
    }

    @Test
    public void testParsingBuyBookHamlet(){
        BookOperation operation = LibraryOperationParser.parseOperation("buy Hamlet");
        assertEquals(LibraryOptionType.LIBRARY_OPTION_UNKNOWN, operation.getOperation());
    }

    @Test
    public void testParsingBuyBookGoneWithTheWind(){
        BookOperation operation = LibraryOperationParser.parseOperation("buy Gone with the wind");
        assertEquals(LibraryOptionType.LIBRARY_OPTION_UNKNOWN, operation.getOperation());
    }
}
