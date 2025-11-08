package com.adarifian.postgresqldb.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.adarifian.postgresqldb.TestDataUtil;
import com.adarifian.postgresqldb.domain.Author;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl authorDaoImpl;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
        this.authorDaoImpl = underTest;
    }

    @Test
    public void testAuthorCanBeCreatedAndFound() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDaoImpl.create(author);
        Optional<Author> results = authorDaoImpl.findOne(author.id());

        assertEquals(results.isPresent(), true);
        assertEquals(author, results.get());
    }

    @Test
    public void testMultipleAuthorsCanBeCreatedAndFound() {
        Author authorC = TestDataUtil.createTestAuthorC();
        authorDaoImpl.create(authorC);

        Author authorD = TestDataUtil.createTestAuthorD();
        authorDaoImpl.create(authorD);

        List<Author> results = authorDaoImpl.find();

        assertEquals(results.size(), 2);
        assertEquals(results.contains(authorC), true);
        assertEquals(results.contains(authorD), true);
    }

    @Test
    public void testUpdateAuthor() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDaoImpl.create(author);

        author = author.withName("James May");
        authorDaoImpl.update(author);

        Optional<Author> result = authorDaoImpl.findOne(author.id());
        assertEquals(result.get(), author);
    }

    @Test
    public void testDeleteAuthor() {
        Author author = TestDataUtil.createTestAuthorA();
        authorDaoImpl.create(author);

        authorDaoImpl.delete(author.id());

        Optional<Author> result = authorDaoImpl.findOne(author.id());
        assertEquals(result.isEmpty(), true);
    }
}
