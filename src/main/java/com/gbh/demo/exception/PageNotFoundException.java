package com.gbh.demo.exception;

import com.gbh.demo.model.Book;

public class PageNotFoundException extends BookLibraryException {
    public PageNotFoundException(int pageId, Book book) {
        super(String.format("<h3>Page id <em>%d</em> in Book <em>%s</em> doesn't exist</h3>", pageId, book.getTitle()));
    }
}
