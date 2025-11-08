package com.adarifian.postgresqldb.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.adarifian.postgresqldb.TestDataUtil;
import com.adarifian.postgresqldb.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testCreateAuthorUsingCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors(id, name, age) VALUES(?, ?, ?)"),
                eq(author.id()), eq(author.name()), eq(author.age()));

    }

    @Test
    public void testFindOneAuthorUsingCorrectSql() {
        Long id = 1L;

        underTest.findOne(id);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(id));
    }

    @Test
    public void testFindManyAuthorsUsingCorrectSql() {

        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
    }

    @Test
    public void testUpdateAuthorUsingCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA().withName("James May");
        underTest.update(author);

        verify(jdbcTemplate).update(
                eq("UPDATE authors SET name = ?, age = ? WHERE id = ?"),
                eq(author.name()), eq(author.age()), eq(author.id()));
    }

    @Test
    public void testDeleteAuthorUsingCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.delete(author.id());

        verify(jdbcTemplate).update(
                eq("DELETE FROM authors WHERE id = ?"),
                eq(author.id()));
    }
}
