package com.project.ShoppingAppBackend.exceptions;

public class InvalidJwtException extends Exception{
    public InvalidJwtException(String message) {
        super(message);
    }

}