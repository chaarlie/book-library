package com.gbh.demo.strategy;

import com.gbh.demo.model.Book;
import com.gbh.demo.model.BookPage;
import com.gbh.demo.util.ConvertTextToHTML;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlainTexDocumentStrategy implements DocumentStrategy {
    @Override
    public String format(Book book) {
        return IntStream.range(0, book.getPages().size()).mapToObj(i ->
                new StringBuilder(String.format("\r\r \t\t\t\t\t\t\t\t\t\t Página %d\r", i+1))
                .append(ConvertTextToHTML.parse(book.getPages().get(i).getContent()))).collect(Collectors.joining());
    }
    @Override
    public String format(BookPage bookPage, int displayId) {
        return new StringBuilder(String.format("\r\r \t\t\t\t\t\t\t\t\t\t Página %d\r", displayId))
                .append(ConvertTextToHTML.parse(bookPage.getContent())).toString();
    }
}
