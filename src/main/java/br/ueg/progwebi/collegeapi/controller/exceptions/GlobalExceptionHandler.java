package br.ueg.progwebi.collegeapi.controller.exceptions;

import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.InvocationTargetException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse error = new ErrorResponse(ex.getCodeError(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(ex.getCodeError()));
    }

    @Data
    public static class ErrorResponse {
        private int code;
        private String message;

        public ErrorResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    @ExceptionHandler({ InvocationTargetException.class, RuntimeException.class, Exception.class })
    public ResponseEntity<Object> handleException(final Throwable e) {
        ErrorResponse error = new ErrorResponse(500, e.getMessage());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(500));
    }
}

