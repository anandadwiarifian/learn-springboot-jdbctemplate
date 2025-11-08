package com.adarifian.postgresqldb.domain;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record Author(Long id, String name, int age) {
}
