package com.twu.biblioteca;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by qiyuesong on 18/6/15.
 */
public class MainMenu {
    private static final String MENU_TITLE = "Main Menu";
    private LinkedList<String> menuList;

    public MainMenu(LinkedList<String> menuList){
        this.menuList = menuList;
    }

    public LinkedList<String> getMenuList(){
        return this.menuList;
    }

    public void showMainMenu(){
        Iterator<String> iterator = this.menuList.iterator();
        String menuContent = MENU_TITLE + "\n";
        while(iterator.hasNext()){
            menuContent += iterator.next() + "\n";
        }
        System.out.print(menuContent);
    }
}
