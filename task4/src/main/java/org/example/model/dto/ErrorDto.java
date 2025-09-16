package org.example.model.dto;

import java.time.OffsetDateTime;

public record ErrorDto(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}