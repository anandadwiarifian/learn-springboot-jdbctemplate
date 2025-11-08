package com.adarifian.postgresqldb.dao;

import java.util.List;
import java.util.Optional;

import com.adarifian.postgresqldb.domain.Author;

public interface AuthorDao {

    void create(Author author);

    Optional<Author> findOne(Long id);

    List<Author> find();

    void update(Author author);

    void delete(Long id);

}
