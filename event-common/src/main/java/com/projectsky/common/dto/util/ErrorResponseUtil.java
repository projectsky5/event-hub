package com.projectsky.common.dto.util;

import com.projectsky.common.dto.ErrorResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@UtilityClass
public class ErrorResponseUtil {

    public static ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, String error){
        return ResponseEntity.status(status)
                .body(
                        new ErrorResponse(status.value(), error, message, LocalDateTime.now())
                );
    }
}
