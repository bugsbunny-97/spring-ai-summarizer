package com.example.summarizer.client;

public class UpstreamException extends RuntimeException {
    private final int status;

    public UpstreamException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() { return status; }
}
