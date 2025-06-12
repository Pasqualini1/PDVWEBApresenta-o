package com.eduardo.pdv_web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ApiExceptionDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    private int status;
    private String path;
    private List<String> errors;

    public ApiExceptionDTO(int status, String path, String error) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.path = path;
        this.errors = List.of(error);
    }

    public ApiExceptionDTO(int status, String path, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.path = path;
        this.errors = errors;
    }
}
