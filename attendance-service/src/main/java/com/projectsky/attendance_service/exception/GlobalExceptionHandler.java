package com.projectsky.attendance_service.exception;

import com.projectsky.common.dto.ErrorResponse;
import com.projectsky.common.exception.EventNotFoundException;
import com.projectsky.common.exception.ExternalServerException;
import com.projectsky.common.util.ErrorResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ErrorResponseUtil.buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", message);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventNotFoundException(EventNotFoundException ex) {
        return ErrorResponseUtil.buildResponse(HttpStatus.NOT_FOUND, "Event not found", ex.getMessage());
    }

    @ExceptionHandler(ExternalServerException.class)
    public ResponseEntity<ErrorResponse> handleExternalServerException(ExternalServerException ex) {
        return ErrorResponseUtil.buildResponse(HttpStatus.BAD_GATEWAY, "External Server Error", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ErrorResponseUtil.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
    }
}
