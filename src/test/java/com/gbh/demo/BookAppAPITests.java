package com.gbh.demo;

import com.gbh.demo.controller.BookController;
import com.gbh.demo.model.Book;
import com.gbh.demo.model.BookPage;
import com.gbh.demo.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookAppAPITests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooks() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray());
    }

    @Test
    public void getOneBook() throws Exception
    {
        List<BookPage> pages = new ArrayList<>();
        pages.add(new BookPage());
        pages.add(new BookPage());

        Book testBook = new Book();
        testBook.setTitle("Some random title");
        testBook.setPages(pages);

        Book savedBook = bookService.createBook(testBook);
        mvc.perform( MockMvcRequestBuilders
                .get(String.format("books/%d/%d", savedBook.getId(), 1))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
