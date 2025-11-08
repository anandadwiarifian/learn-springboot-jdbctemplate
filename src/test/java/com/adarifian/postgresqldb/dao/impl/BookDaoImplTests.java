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
import com.adarifian.postgresqldb.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testCreateBookUsingCorrectSql() {
        Book book = TestDataUtil.createTestBookA();
        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books(isbn, title, author_id) VALUES(?, ?, ?)"),
                eq(book.isbn()), eq(book.title()), eq(book.authorId()));
    }

    @Test
    public void testFindOneBookUsingCorrectSql() {
        String isbn = "123";

        underTest.findOne(isbn);

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq(isbn));
    }

    @Test
    public void testFindManyBooksUsingCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
    }

    @Test
    public void testUpdateBookUsingCorrectSql() {
        Book book = TestDataUtil.createTestBookA().withTitle("The Hobbit");
        underTest.update(book);

        verify(jdbcTemplate).update(
                eq("UPDATE books SET title = ?, author_id = ? WHERE isbn = ?"),
                eq(book.title()), eq(book.authorId()), eq(book.isbn()));
    }

    @Test
    public void testDeleteBookUsingCorrectSql() {
        Book book = TestDataUtil.createTestBookA();
        underTest.delete(book.isbn());

        verify(jdbcTemplate).update(
                eq("DELETE FROM books WHERE isbn = ?"),
                eq(book.isbn()));
    }
}
