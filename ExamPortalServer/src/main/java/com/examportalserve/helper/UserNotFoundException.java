package com.examportalserve.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User with this username not found.");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
