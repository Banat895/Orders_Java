package com.test.orders.exception;

import com.test.orders.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDto errorDto = new ErrorDto();
        e.getBindingResult().getAllErrors().stream().limit(1).forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorDto.setErrorCode("400");
            errorDto.setFieldName(fieldName);
            errorDto.setMessage(errorMessage);
        });
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<ErrorDto> handleArticleNotFoundException(OrderNotFoundException e) {
        ErrorDto dto = new ErrorDto();
        dto.setMessage(e.getMessage());
        dto.setErrorCode("404");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
