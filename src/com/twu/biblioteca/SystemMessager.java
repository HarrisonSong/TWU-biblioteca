package com.twu.biblioteca;

import static com.twu.biblioteca.SystemMessageContants.*;

/**
 * Created by qiyuesong on 18/6/15.
 */
public class SystemMessager {
    public enum  SystemMessageType {
        INVALID_MENU_OPTION,
        SUCCESSFUL_CHECKOUT,
        UNSUCCESSFUL_CHECKOUT,
        SUCCESSFUL_RETURN,
        UNSUCCESSFUL_RETURN,
        INVALID_BOOK_OPTION
    }


    public static void showWelcomeMessage(){
        System.out.println(WELCOME_MESSAGE);
    }

    public static void showRemindingMessage(){
        System.out.print(REMINDING_MESSAGE);
    }

    public static void showResponseMessage(SystemMessageType type){
        String messageForResponse = "";
        switch(type){
            case INVALID_MENU_OPTION:{
                messageForResponse = INVALID_MENU_OPTION_MESSAGE;
                break;
            }

            case SUCCESSFUL_CHECKOUT:{
                messageForResponse = SUCCESSFUL_CHECKOUT_MESSAGE;
                break;
            }

            case UNSUCCESSFUL_CHECKOUT:{
                messageForResponse = UNSUCCESSFUL_CHECKOUT_MESSAGE;
                break;
            }

            case SUCCESSFUL_RETURN:{
                messageForResponse = SUCCESSFUL_RETURN_MESSAGE;
                break;
            }

            case UNSUCCESSFUL_RETURN:{
                messageForResponse = UNSUCCESSFUL_RETURN_MESSAGE;
                break;
            }

            case INVALID_BOOK_OPTION:{
                messageForResponse = INVALID_BOOK_OPTION_MESSAGE;
                break;
            }
            default:
                break;
        }
        System.out.println(messageForResponse);
    }
}
