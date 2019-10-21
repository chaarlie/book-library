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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        bookService.deleteBook(id);
        return id;
    }
    @GetMapping(value = "/html/{id}", produces = { MediaType.TEXT_HTML_VALUE })
    String getBookInHTML(@PathVariable int id) throws BookNotFoundException {
        Book book = bookService.findBookById(id).orElseThrow( () -> new BookNotFoundException(id));

        DocumentStrategy strategy = documentStrategyFactory.createDocument("HTML");

        return IntStream.range(0, book.getPages().size()).mapToObj( i -> {
            return new StringBuilder(String.format("<h4 style='text-align:center'> Página %d </h4>", i+1))
                    .append(strategy.format(book, i));
        }).collect(Collectors.joining());
    }

    @PostMapping(value = "/html/{id}")
    BookPage addHTMLPage(@PathVariable int id, @RequestBody  BookPage page) throws BookLibraryException {
        return addPage(id, page);
    }

    @PutMapping(value = "/html/{bookId}/{pageId}")
    BookPage updateHTMLPage(@PathVariable int bookId, @PathVariable int pageId,  @RequestBody  BookPage page) throws BookLibraryException {
        return updatePage(bookId, pageId, page);
    }

    @DeleteMapping(value = "/html/{bookId}/{pageId}")
    int deleteHTMLPage(@PathVariable int bookId, @PathVariable int pageId) throws BookLibraryException {
       return deletePage(bookId, pageId);
    }

    @PostMapping(value = "/text/{id}")
    BookPage addPlainTextPage(@PathVariable int id, @RequestBody  BookPage page) throws BookLibraryException {
        return addPage(id, page);
    }

    @PutMapping(value = "/text/{id}/{pageId}/{bookId}")
    BookPage updatePlainTextPage(@PathVariable int bookId, @PathVariable int pageId,  @RequestBody  BookPage page) throws BookLibraryException {
        return updatePage(bookId, pageId, page);
    }

    @DeleteMapping(value = "/text/{id}/{pageId}/{bookId}")
    int deletePlainTextPage(@PathVariable int bookId, @PathVariable int pageId) throws BookLibraryException {
        return deletePage(bookId, pageId);
    }

    @GetMapping(value = "/html/{id}/{page}", produces = { MediaType.TEXT_HTML_VALUE })
    String getBookInHTMLByPage(@PathVariable int id, @PathVariable int page) throws BookNotFoundException {
        Book htmlBook = bookService.findBookById(id).orElseThrow( () -> new BookNotFoundException(id));
        DocumentStrategy strategy = documentStrategyFactory.createDocument("HTML");
        return strategy.format(htmlBook, page-1);
    }
    @GetMapping(value = "/text/{id}", produces = { MediaType.TEXT_PLAIN_VALUE })
    String getBookInPlainText(@PathVariable int id) throws BookNotFoundException {
        Book book = bookService.findBookById(id).orElseThrow( () -> new BookNotFoundException(id));

        return IntStream.range(0, book.getPages().size()).mapToObj( i -> {
            return new StringBuilder(String.format("\r\r \t\t\t\t\t\t\t\t\t\t Página %d\r", i+1))
                    .append(book.getPages().get(i).getContent());
        }).collect(Collectors.joining());
    }
    @GetMapping(value = "/text/{id}/{page}", produces = { MediaType.TEXT_PLAIN_VALUE })
    String getBookInPlainTextByPage(@PathVariable int id, @PathVariable int page) throws BookNotFoundException {
        Book book = bookService.findBookById(id).orElseThrow( () -> new BookNotFoundException(id));
        return book.getPages().get(page-1).getContent();
    }

    BookPage addPage(int id, BookPage page) throws BookLibraryException {
        return bookService.createPage(id, page);
    }

    BookPage updatePage(int bookId, int pageId,  BookPage page) throws BookLibraryException {
        return bookService.updatePage(bookId, pageId, page);
    }

    int deletePage(int id, int pageId) throws BookLibraryException {
        return bookService.deletePage(id, pageId);
    }
}
