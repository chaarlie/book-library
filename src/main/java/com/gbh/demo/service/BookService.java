package com.gbh.demo.service;

import com.gbh.demo.exception.BookLibraryException;
import com.gbh.demo.exception.BookNotFoundException;
import com.gbh.demo.exception.PageNotFoundException;
import com.gbh.demo.model.Book;
import com.gbh.demo.model.BookPage;
import com.gbh.demo.repository.BookPageRepository;
import com.gbh.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPageRepository bookPageRepository;

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
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

    public int deleteBook(int id) throws BookLibraryException {
        bookRepository.findById(id).orElseThrow( () -> new BookNotFoundException(id));

        bookRepository.deleteById(id);

        return id;
    }

    public BookPage findPage(int bookId, int pageId) throws BookLibraryException {
        Book book = bookRepository.findById(bookId).orElseThrow( () -> new BookNotFoundException(bookId));

        List<BookPage> pages = IntStream.range(0, book.getPages().size()).mapToObj(i -> {
            if(i == pageId) return book.getPages().get(i);
            return null;
        }).filter(p-> p!= null).collect(Collectors.toList());

        if(pages == null || pages.size() == 0) throw new PageNotFoundException(pageId, book);

        return pages.get(0);
    }

    public BookPage createPage(int id, BookPage page) throws BookLibraryException {
        Book book = bookRepository.findById(id).orElseThrow( () -> new BookNotFoundException(id));

        page.setBook(book);
        BookPage savedPage = bookPageRepository.save(page);

        book.getPages().add(savedPage);
        bookRepository.save(book);

        return savedPage;
    }

    public BookPage updatePage(int bookId, int pageId, BookPage page) throws BookLibraryException {

        BookPage foundPage = findPage(bookId, pageId);
        foundPage.setContent(page.getContent());

        return bookPageRepository.save(foundPage);
    }

    public int deletePage(int bookId, int pageId) throws BookLibraryException {
        Book book = bookRepository.findById(bookId).orElseThrow( () -> new BookNotFoundException(bookId));
        int deleteId = findPage(bookId, pageId).getId();

        bookPageRepository.deleteById(deleteId);

        book.getPages().removeIf( p -> p.getId() == deleteId);
        bookRepository.save(book);

        return deleteId;
    }
}