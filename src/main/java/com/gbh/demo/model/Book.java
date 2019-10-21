package com.gbh.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter @Setter
    private String title;
    @Getter @Setter
    @OneToMany(mappedBy = "book", cascade = { CascadeType.REMOVE }, targetEntity = BookPage.class, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<BookPage> pages;
}
