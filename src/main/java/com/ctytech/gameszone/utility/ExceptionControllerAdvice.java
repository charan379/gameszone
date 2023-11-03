package com.ctytech.gameszone.utility;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ctytech.gameszone.exception.GameszoneException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    Environment environment;

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
        //
        ErrorInfo error = new ErrorInfo();
        //
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
        error.setTimestamp(LocalDateTime.now());
        //
        return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GameszoneException.class)
    public ResponseEntity<ErrorInfo> gameszoneExceptionHandler(GameszoneException exception) {
        //
        ErrorInfo error = new ErrorInfo();
        //
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(environment.getProperty(exception.getMessage()));
        error.setTimestamp(LocalDateTime.now());
        //
        return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
    }
}
