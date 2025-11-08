package com.adarifian.postgresqldb.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.adarifian.postgresqldb.TestDataUtil;
import com.adarifian.postgresqldb.domain.Book;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {

    private BookDaoImpl bookDaoImpl;
    private AuthorDaoImpl authorDaoImpl;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest, AuthorDaoImpl authorDaoImpl) {
        this.bookDaoImpl = underTest;
        this.authorDaoImpl = authorDaoImpl;
    }

    @Test
    public void testBookCanBeCreatedAndFound() {
        authorDaoImpl.create(TestDataUtil.createTestAuthorB());

        Book book = TestDataUtil.createTestBookA();
        bookDaoImpl.create(book);
        var result = bookDaoImpl.findOne(book.isbn());

        assertEquals(result.isPresent(), true);
        assertEquals(book, result.get());
    }

    @Test
    public void testMultipleBooksCanBeCreatedAndFound() {
        authorDaoImpl.create(TestDataUtil.createTestAuthorE());
        authorDaoImpl.create(TestDataUtil.createTestAuthorF());

        Book bookB = TestDataUtil.createTestBookB();
        bookDaoImpl.create(bookB);

        Book bookC = TestDataUtil.createTestBookC();
        bookDaoImpl.create(bookC);

        List<Book> results = bookDaoImpl.find();
        assertEquals(results.size(), 2);
        assertEquals(results.contains(bookB), true);
        assertEquals(results.contains(bookC), true);
    }

    @Test
    public void testUpdateBook() {
        authorDaoImpl.create(TestDataUtil.createTestAuthorB());
        Book book = TestDataUtil.createTestBookA();
        bookDaoImpl.create(book);

        book = book.withTitle("The Hobbit");
        bookDaoImpl.update(book);

        Optional<Book> result = bookDaoImpl.findOne(book.isbn());

        assertEquals(result.get(), book);
    }

    @Test
    public void testDeleteBook() {
        Book book = TestDataUtil.createTestBookA();
        authorDaoImpl.create(TestDataUtil.createTestAuthorB());
        bookDaoImpl.create(book);

        bookDaoImpl.delete(book.isbn());

        Optional<Book> result = bookDaoImpl.findOne(book.isbn());
        assertEquals(result.isEmpty(), true);
    }
}
