package com.nackademin.webshopbackend.utils;

/**
 * Created by Ashkan Amiri
 * Date:  2021-04-22
 * Time:  22:27
 * Project: webshop-backend
 * Copyright: MIT
 * This class handles Exceptions thrown in services and controller classes.
 */

public class UserException extends Exception{

    public UserException() {}

    public UserException(String message){
        super(message);
    }
}
