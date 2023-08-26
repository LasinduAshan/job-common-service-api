package com.job.common.controller.errors;


import com.job.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class ErrorTranslator {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleNoSuchElementException(RecordNotFoundException ex, NativeWebRequest request) {
        log.error(ex.getMessage());
        ErrorDto errorDto = new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleJdbcException(JdbcException ex, NativeWebRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(Exception ex, NativeWebRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleRequiredValueException(RequiredValueException ex, NativeWebRequest request) {
        log.error(ex.getMessage());
        ErrorDto errorDto = new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleUnAuthorizeException(UnAuthorizeException ex, NativeWebRequest request) {
        log.error(ex.getMessage());
        ErrorDto errorDto = new ErrorDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleAlreadyExistElementException(AlreadyExistsException ex, NativeWebRequest request) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
}
