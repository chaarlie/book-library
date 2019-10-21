package com.gbh.demo.repository;

import com.gbh.demo.model.BookPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPageRepository extends CrudRepository<BookPage, Integer> { }
