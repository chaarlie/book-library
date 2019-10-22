package com.gbh.demo.controller;

import com.gbh.demo.exception.BookLibraryException;
import com.gbh.demo.exception.BookNotFoundException;
import com.gbh.demo.model.Book;
import com.gbh.demo.model.BookPage;
import com.gbh.demo.service.BookService;
import com.gbh.demo.strategy.DocumentStrategy;
import com.gbh.demo.strategy.DocumentStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    DocumentStrategyFactory documentStrategyFactory;

    @GetMapping("/")
    List<Book> findAllBooks ()  {
        return bookService.findAllBooks();
    }
    @GetMapping("/{id}")
    Book findOneBook (@PathVariable int id) throws BookNotFoundException {
        return bookService.findBookById(id).orElseThrow( () -> new BookNotFoundException(id));
    }
    @PostMapping("/")
    Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    @PutMapping("/{id}")
    Book updateBook(@RequestBody Book book, @PathVariable int id) throws BookLibraryException {
        return bookService.updateBook(id, book);
    }
    @DeleteMapping("/{id}")
    int deleteBook(@PathVariable int id) throws BookLibraryException {
        return bookService.deleteBook(id);
    }
    @GetMapping(value = "/{id}/html", produces = { MediaType.TEXT_HTML_VALUE })
    String getBookInHTML(@PathVariable int id) throws BookNotFoundException {
        Book book = bookService.findBookById(id).orElseThrow( () -> new BookNotFoundException(id));

        DocumentStrategy strategy = documentStrategyFactory.createDocument("HTML");
        return strategy.format(book);
    }
    @GetMapping(value = "/{id}/text", produces = { MediaType.TEXT_PLAIN_VALUE })
    String getBookInPlainText(@PathVariable int id) throws BookNotFoundException {
        Book book = bookService.findBookById(id).orElseThrow( () -> new BookNotFoundException(id));

        DocumentStrategy strategy = documentStrategyFactory.createDocument("PLAIN");
        return strategy.format(book);
    }
    @PostMapping(value = { "/{id}/html", "/{id}/text" })
    BookPage createPage(@PathVariable int id, @RequestBody  BookPage page) throws BookLibraryException {
        return bookService.createPage(id, page);
    }
    @PutMapping(value = { "/{bookId}/page/{pageId}/html", "/{bookId}/page/{pageId}/text" })
    BookPage updatePage(@PathVariable int bookId, @PathVariable int pageId,  @RequestBody  BookPage page) throws BookLibraryException {
        return bookService.updatePage(bookId, pageId-1, page);
    }
    @DeleteMapping(value = { "/{bookId}/page/{pageId}/html", "/{bookId}/page/{pageId}/text" })
    int deletePage(@PathVariable int bookId, @PathVariable int pageId) throws BookLibraryException {
        return bookService.deletePage(bookId, pageId-1);
    }
    @GetMapping(value = "/{bookId}/page/{pageId}/html", produces = { MediaType.TEXT_HTML_VALUE })
    String getBookInHTMLByPage(@PathVariable int bookId, @PathVariable int pageId) throws BookLibraryException {
        DocumentStrategy strategy = documentStrategyFactory.createDocument("HTML");

        return strategy.format(bookService.findPage(bookId, pageId-1), pageId);
    }
    @GetMapping(value = "/{bookId}/page/{pageId}/text", produces = { MediaType.TEXT_PLAIN_VALUE })
    String getBookInPlainTextByPage(@PathVariable int bookId, @PathVariable int pageId) throws BookLibraryException {
        DocumentStrategy strategy = documentStrategyFactory.createDocument("PLAIN");

        return strategy.format(bookService.findPage(bookId, pageId-1), pageId);
    }
}
