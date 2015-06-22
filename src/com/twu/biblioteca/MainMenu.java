package com.twu.biblioteca;

import java.util.Iterator;
import java.util.LinkedList;

import static com.twu.biblioteca.MainMenuOptionConstants.*;

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

    public void showMainMenu(boolean shouldShowUserInfoOption){
        Iterator<String> iterator = this.menuList.iterator();
        String menuContent = MENU_TITLE + "\n";
        while(iterator.hasNext()){
            String currentOption = iterator.next();
            if(!currentOption.equals(MAIN_MENU_SHOW_CUSTOMER_INFORMATION_OPTION) || shouldShowUserInfoOption){
                menuContent += currentOption + "\n";
            }
        }
        System.out.print(menuContent);
    }

    public String checkOperation(String operation){
        String operationContent = operation.trim().toLowerCase();
        for(String option : this.menuList){
            if(option.toLowerCase().equals(operationContent)){
                return option;
            }
        }
        return "";
    }
}
