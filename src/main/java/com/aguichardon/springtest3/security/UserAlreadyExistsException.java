package com.aguichardon.springtest3.security;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message)  {
        super(message);
    }
}
