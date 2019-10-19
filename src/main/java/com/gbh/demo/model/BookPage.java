package com.gbh.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "book_page")
public class BookPage {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Getter @Setter
    private String content;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="book_id",referencedColumnName="id", nullable = false, unique = true)
    @JsonManagedReference
    private  Book book;
}
