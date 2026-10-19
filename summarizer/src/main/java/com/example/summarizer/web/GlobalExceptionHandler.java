package com.example.summarizer.web;

import com.example.summarizer.client.UpstreamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UpstreamException.class)
    ProblemDetail upstream(UpstreamException e) {
        log.error("Upstream failure: {}", e.getMessage());
        // Map provider errors to a gateway-style status; never leak the raw body to callers
        HttpStatus status = switch (e.getStatus()) {
            case 429 -> HttpStatus.TOO_MANY_REQUESTS;
            case 504 -> HttpStatus.GATEWAY_TIMEOUT;
            default -> HttpStatus.BAD_GATEWAY;
        };
        return ProblemDetail.forStatusAndDetail(status, "Summarisation provider failed");
    }
}
