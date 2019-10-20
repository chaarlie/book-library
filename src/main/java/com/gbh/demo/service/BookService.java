package com.gbh.demo.service;

import com.gbh.demo.model.Book;
import com.gbh.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(int id) {
        return repository.findById(id);
    }
}