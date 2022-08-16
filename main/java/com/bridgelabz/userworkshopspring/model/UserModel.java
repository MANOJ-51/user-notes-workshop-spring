package com.bridgelabz.userworkshopspring.model;

import com.bridgelabz.userworkshopspring.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_details_spring")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String LastName;
    private String emailId;
    private String password;
    private LocalDateTime registeredDate;
    private LocalDateTime updatedDate;

    public UserModel(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.LastName = userDTO.getLastName();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();
    }

    public UserModel() {
    }
}
