package com.job.common.controller.errors;


import com.job.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;


@Slf4j
@ControllerAdvice
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
    public ResponseEntity<ErrorDto> handleBadRequestAlertException(BadRequestException ex, NativeWebRequest request) {
        log.error(ex.getMessage());
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleRequiredValueException(RequiredValueException ex, NativeWebRequest request) {
        log.error(ex.getMessage());
        ErrorDto errorDto = new ErrorDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorDto);
    }

/*
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleBadRequestAlertException(MethodArgumentNotValidException ex, NativeWebRequest request) {
        ValidationErrorDto errorDto = new ValidationErrorDto(HttpStatus.BAD_REQUEST.value(), "Validation failure");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            InvalidField invalidField = new InvalidField();
            invalidField.setField(error.getField());
            invalidField.setErrorMessage(error.getDefaultMessage());
            errorDto.getInvalidFields().add(invalidField);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
*/

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
}
