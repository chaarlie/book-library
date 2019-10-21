package com.gbh.demo.strategy;

import com.gbh.demo.model.Book;
import com.gbh.demo.model.BookPage;

public interface DocumentStrategy {
    String format(Book book);
    String format(BookPage bookPage, int displayId);
}
