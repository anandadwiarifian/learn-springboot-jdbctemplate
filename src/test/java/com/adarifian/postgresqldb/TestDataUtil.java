package com.adarifian.postgresqldb;

import com.adarifian.postgresqldb.domain.Author;
import com.adarifian.postgresqldb.domain.Book;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("James")
                .age(75)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Tolkien")
                .age(30)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Richard")
                .age(30)
                .build();
    }

    public static Author createTestAuthorD() {
        return Author.builder()
                .id(4L)
                .name("Jeremy")
                .age(30)
                .build();
    }

    public static Author createTestAuthorE() {
        return Author.builder()
                .id(5L)
                .name("GoT Writer")
                .age(30)
                .build();
    }

    public static Author createTestAuthorF() {
        return Author.builder()
                .id(6L)
                .name("Sun Tzu")
                .age(30)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("123")
                .title("LOTR")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("1234")
                .title("Game of Thrones")
                .authorId(5L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("12345")
                .title("Art of War")
                .authorId(6L)
                .build();
    }

}
