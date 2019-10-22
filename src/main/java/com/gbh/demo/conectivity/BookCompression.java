package com.gbh.demo.conectivity;

import com.gbh.demo.model.Book;

public interface BookCompression<T> {
    T compress(Book book);
}
