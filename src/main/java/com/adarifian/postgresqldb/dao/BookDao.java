package com.adarifian.postgresqldb.dao;

import java.util.List;
import java.util.Optional;

import com.adarifian.postgresqldb.domain.Book;

public interface BookDao {

    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();

    void update(Book book);

    void delete(String isbn);

}
