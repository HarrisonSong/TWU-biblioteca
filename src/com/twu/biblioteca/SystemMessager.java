package com.twu.biblioteca;

import static com.twu.biblioteca.SystemMessageContants.*;
import static com.twu.biblioteca.SystemMessageContants.INVALID_BOOK_OPTION_MESSAGE;
import static com.twu.biblioteca.SystemMessageTypeContants.*;
import static com.twu.biblioteca.SystemMessageTypeContants.INVALID_BOOK_OPTION_MESSAGE_TYPE;
import static com.twu.biblioteca.SystemMessageTypeContants.UNSUCCESSFUL_RETURN_MESSAGE_TYPE;

/**
 * Created by qiyuesong on 18/6/15.
 */
public class SystemMessager {

    public static void showWelcomeMessage(){
        System.out.println(WELCOME_MESSAGE);
    }

    public static void showRemindingMessage(){
        System.out.print(REMINDING_MESSAGE);
    }

    public static void showResponseMessage(String type){
        if(type.equals(INVALID_MENU_OPTION_MESSAGE_TYPE)){
            System.out.println(INVALID_MENU_OPTION_MESSAGE);
        }else if(type.equals(SUCCESSFUL_CHECKOUT_MESSAGE_TYPE)){
            System.out.println(SUCCESSFUL_CHECKOUT_MESSAGE);
        }else if(type.equals(UNSUCCESSFUL_CHECKOUT_MESSAGE_TYPE)){
            System.out.println(UNSUCCESSFUL_CHECKOUT_MESSAGE);
        }else if(type.equals(SUCCESSFUL_RETURN_MESSAGE_TYPE)){
            System.out.println(SUCCESSFUL_RETURN_MESSAGE);
        }else if(type.equals(UNSUCCESSFUL_RETURN_MESSAGE_TYPE)){
            System.out.println(UNSUCCESSFUL_RETURN_MESSAGE);
        }else if(type.equals(INVALID_BOOK_OPTION_MESSAGE_TYPE)){
            System.out.println(INVALID_BOOK_OPTION_MESSAGE);
        }
    }
}
