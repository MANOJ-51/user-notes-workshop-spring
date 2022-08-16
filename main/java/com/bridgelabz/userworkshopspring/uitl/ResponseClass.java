package com.bridgelabz.userworkshopspring.uitl;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseClass {
    private int errorCode;
    private String errorMessage;
    private Object token;

    public ResponseClass() {
    }
}
