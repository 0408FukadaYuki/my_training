package com.example.demo;

import lombok.Data;

@Data
public class ErrorResponseBody {
    private int status;
    private String errorMessage;
}
