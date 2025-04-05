package br.ueg.progwebi.collegeapi.controller.exceptions;

import br.ueg.progwebi.collegeapi.service.exceptions.BusinessException;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}

