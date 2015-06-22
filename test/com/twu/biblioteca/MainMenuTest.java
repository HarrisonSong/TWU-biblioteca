package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static com.twu.biblioteca.MainMenuOptionConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by qiyuesong on 18/6/15.
 */
public class MainMenuTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private MainMenu menu;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outStream));

        LinkedList<String> menuList = new LinkedList<String>();
        menuList.add(MAIN_MENU_LIST_BOOKS_OPTION);
        menuList.add(MAIN_MENU_LIST_MOVIES_OPTION);
        menu = new MainMenu(menuList);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void testMenuList(){
        assertEquals(2, menu.getMenuList().size());
        assertTrue(menu.getMenuList().contains(MAIN_MENU_LIST_BOOKS_OPTION));
    }

    @Test
    public void testShowMenu(){
        menu.showMainMenu();
        assertEquals("Main Menu\nList Books\nList Movies\n", outStream.toString());
    }
}
