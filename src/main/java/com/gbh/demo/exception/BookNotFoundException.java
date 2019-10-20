package com.gbh.demo.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(int bookId) {
        super(String.format("That book id=%d doesn't exist", bookId));
    }
}
