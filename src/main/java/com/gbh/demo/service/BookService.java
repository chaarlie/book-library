package com.gbh.demo.service;

import com.gbh.demo.exception.BookLibraryException;
import com.gbh.demo.exception.BookNotFoundException;
import com.gbh.demo.exception.PageNotFoundException;
import com.gbh.demo.model.Book;
import com.gbh.demo.model.BookPage;
import com.gbh.demo.repository.BookPageRepository;
import com.gbh.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookPageRepository bookPageRepository;

    public BookPage createPage(int id, BookPage page) throws BookLibraryException {
        Book book = bookRepository.findById(id).orElseThrow( () -> new BookNotFoundException(id));

        page.setBook(book);
        BookPage savedPage = bookPageRepository.save(page);

        book.getPages().add(savedPage);
        bookRepository.save(book);

        return savedPage;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book book) throws BookLibraryException {
        Optional<Book> foundBook = bookRepository.findById(id);
        if(foundBook.isPresent()) {
            foundBook.get().setPages(book.getPages());
            foundBook.get().setTitle(book.getTitle());
        }
        else throw new BookNotFoundException(id);
        return bookRepository.save(foundBook.get());
    }

    public void deleteBook(int id) throws BookLibraryException {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            bookRepository.deleteById(id);
        }
        else throw new BookNotFoundException(id);
    }

    public BookPage updatePage(int bookId, int pageId, BookPage page) throws BookLibraryException {
        Book book = bookRepository.findById(bookId).orElseThrow( () -> new BookNotFoundException(bookId));

        book.getPages()
                .stream()
                .fl((p) -> p.getId() == pageId)
                .findAny().orElseThrow(() -> new PageNotFoundException(pageId, book));

        book.getPages().get(pageId-1).setContent(page.getContent());
        bookRepository.save(book);

        return book.getPages().get(pageId-1);
    }

    public int deletePage(int id, int pageId) throws BookLibraryException {
        Book book = bookRepository.findById(id).orElseThrow( () -> new BookNotFoundException(id));
        if(!book.getPages().contains(pageId-1)) throw new PageNotFoundException(pageId, book);

        book.getPages().remove(pageId-1);
        bookRepository.save(book);

        return pageId+1;
    }
}