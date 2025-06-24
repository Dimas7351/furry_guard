package com.app.furryguard.exceptions;


import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record CustomizedException(
        String message,
        HttpStatus httpStatus,
        ZonedDateTime timestamp
) {
}