package com.examportalserve.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("User with this User name is already registered, try with different username");
    }
  public UserFoundException(String msg){
        super(msg);
  }
}
