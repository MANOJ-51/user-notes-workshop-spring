package com.bridgelabz.userworkshopspring.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String firstName;
    private String LastName;
    private String emailId;
    private String password;
}
