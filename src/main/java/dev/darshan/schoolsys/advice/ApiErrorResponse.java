package dev.darshan.schoolsys.advice;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        HttpStatusCode status,
        String error,
        String message,
        LocalDateTime timestamp
){

}
