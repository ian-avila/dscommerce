package com.dev_ian.dscommerce.dto;

import org.springframework.http.HttpStatus;

import java.net.URI;
import java.time.Instant;

public class CustomError {
    private Instant timestamp;
    private int status;
    private String errorMessage;
    private String path;

    public CustomError(Instant timestamp, int status, String errorMessage, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.errorMessage = errorMessage;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getPath() {
        return path;
    }
}
