package com.gbh.demo.controller;

import com.gbh.demo.exception.BookNotFoundException;
import com.gbh.demo.model.Book;
import com.gbh.demo.service.BookService;
import com.gbh.demo.strategy.DocumentStrategy;
import com.gbh.demo.strategy.DocumentStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController()
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    DocumentStrategyFactory documentStrategyFactory;

    @GetMapping("/")
    List<Book> findAllBooks ()  {
        return bookService.findAll();
    }

    @GetMapping(value = "/html/{id}/{page}", produces = { MediaType.TEXT_HTML_VALUE })
    String getBookInHtmlByPage(@PathVariable int id, @PathVariable int page) throws BookNotFoundException {
        Book htmlBook = bookService.findById(id).orElseThrow( () -> new BookNotFoundException(id));
        DocumentStrategy strategy = documentStrategyFactory.createDocument("HTML");
        return strategy.format(htmlBook, page);
    }

    @GetMapping(value = "/text/{id}/{page}", produces = { MediaType.TEXT_PLAIN_VALUE })
    String getBookInPlainTextByPage(@PathVariable int id, @PathVariable int page) throws BookNotFoundException {
        Book book = bookService.findById(id).orElseThrow( () -> new BookNotFoundException(id));
        return book.getPages().get(page-1).getContent();
    }
    @GetMapping(value = "/text/{id}", produces = { MediaType.TEXT_PLAIN_VALUE })
    String getBookInPlainText(@PathVariable int id) throws BookNotFoundException {
        Book book = bookService.findById(id).orElseThrow( () -> new BookNotFoundException(id));

        return IntStream.range(0, book.getPages().size()).mapToObj( i -> {
            return new StringBuilder(String.format("\r \t\t\t\t\t\t\t\t\t\t Página %d\r", i+1))
                    .append(book.getPages().get(i).getContent());
        }).collect(Collectors.joining());
    }
}
