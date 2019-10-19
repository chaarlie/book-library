package com.gbh.demo.controller;

import com.gbh.demo.model.Book;
import com.gbh.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    List<Book> findALLBooks() {
        return bookService.findAll();
    }
}
