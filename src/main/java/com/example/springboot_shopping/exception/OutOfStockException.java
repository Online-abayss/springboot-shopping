package com.example.springboot_shopping.exception;

public class OutOfStockException extends RuntimeException{

    public OutOfStockException(String message) {

        super(message);
    }
}
