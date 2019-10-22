package com.gbh.demo.conectivity;

public interface BookCompressFormatData<T> {
    T getBookById(int id);
    T getAllBooks();
    T createBook(T book);
    T updateBook(T book);
    T deleteBook(int id);
}
