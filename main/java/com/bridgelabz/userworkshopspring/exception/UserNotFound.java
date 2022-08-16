package com.bridgelabz.userworkshopspring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
@AllArgsConstructor
public class UserNotFound extends RuntimeException {
    private int errorCode;
    private String errorMessage;

}
