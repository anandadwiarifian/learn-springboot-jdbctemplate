package com.adarifian.postgresqldb.domain;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record Book(String isbn, String title, Long authorId) {
}
