package com.gbh.demo.strategy;

import com.gbh.demo.model.Book;
import com.gbh.demo.model.BookPage;
import com.gbh.demo.util.ConvertTextToHTML;

import java.util.List;

public class HTMLDocumentStrategy implements DocumentStrategy {

    @Override
    public String format(Book b) {
        return null;
    }

    @Override
    public String format(Book b, int page) {
        List<BookPage> pages = b.getPages();
        return ConvertTextToHTML.parse(pages.get(page).getContent());
    }
}
