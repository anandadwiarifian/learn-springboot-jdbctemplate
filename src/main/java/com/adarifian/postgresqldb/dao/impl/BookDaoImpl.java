package com.adarifian.postgresqldb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.adarifian.postgresqldb.dao.BookDao;
import com.adarifian.postgresqldb.domain.Book;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books(isbn, title, author_id) VALUES(?, ?, ?)", book.isbn(),
                book.title(), book.authorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(), isbn);
        return results.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookRowMapper());
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update(
                "UPDATE books SET title = ?, author_id = ? WHERE isbn = ?",
                book.title(), book.authorId(), book.isbn());
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?",
                isbn);
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet arg0, int arg1) throws SQLException {
            return Book.builder()
                    .isbn(arg0.getString("isbn"))
                    .title(arg0.getString("title"))
                    .authorId(arg0.getLong("author_id"))
                    .build();
        }
    }

}
