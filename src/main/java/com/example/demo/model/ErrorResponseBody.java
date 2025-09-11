package com.example.demo.model;
import lombok.Data;

@Data
public class ErrorResponseBody {
    private int status;
    private String errorMessage;
}
