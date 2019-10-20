package com.gbh.demo.strategy;

import com.gbh.demo.model.Book;

public interface DocumentStrategy {
    String format(Book b);
    String format(Book b, int page);
}
