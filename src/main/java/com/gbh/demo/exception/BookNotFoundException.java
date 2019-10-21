package com.gbh.demo.exception;

public class BookNotFoundException extends BookLibraryException {
    public BookNotFoundException(int bookId) {
        super(String.format("<h3>Book id <em>%d</em> doesn't exist</h3>", bookId));
    }
}
