package com.bridgelabz.userworkshopspring.exception;

import com.bridgelabz.userworkshopspring.uitl.ResponseClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseClass> handleException(UserNotFound userNotFound) {
        ResponseClass responseClass = new ResponseClass();
        responseClass.setErrorCode(400);
        responseClass.setErrorMessage(userNotFound.getErrorMessage());
        return new ResponseEntity<>(responseClass, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
