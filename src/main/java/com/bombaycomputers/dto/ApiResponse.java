package com.bombaycomputers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String statusCode;
    private String message;
    private String error;
    private String status;
}