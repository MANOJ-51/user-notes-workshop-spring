package com.bridgelabz.userworkshopspring.service;

import com.bridgelabz.userworkshopspring.dto.UserDTO;
import com.bridgelabz.userworkshopspring.model.UserModel;
import com.bridgelabz.userworkshopspring.uitl.ResponseClass;

import java.util.List;

public interface IUserService {
    UserModel userCreate(UserDTO userDTO);

    UserModel editUser(String token, UserDTO userDTO);

    List<UserModel> viewList(String token);

    UserModel userDelete(String token);

    ResponseClass tokenGeneration(String email, String password);
}
