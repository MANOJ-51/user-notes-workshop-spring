package com.bridgelabz.userworkshopspring.controller;

import com.bridgelabz.userworkshopspring.dto.UserDTO;
import com.bridgelabz.userworkshopspring.model.UserModel;
import com.bridgelabz.userworkshopspring.service.IUserService;
import com.bridgelabz.userworkshopspring.uitl.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    IUserService iUserService;

    //welcome note
    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to User Registration Spring";
    }

    //create user
    @PostMapping("/createUser")
    public UserModel createUser(@RequestBody UserDTO userDTO) {
        return iUserService.userCreate(userDTO);
    }

    //updateUser
    @PutMapping("/updateUser/{id}")
    public UserModel updateUser(@RequestHeader String token, @RequestBody UserDTO userDTO) {
        return iUserService.editUser(token, userDTO);
    }

    //retrieve data
    @GetMapping("/getUserList")
    public List<UserModel> getList(@RequestHeader String token) {
        return iUserService.viewList(token);
    }

    //delete user
    @DeleteMapping("/deleteUser")
    public UserModel deleteUser(@RequestHeader String token) {
        return iUserService.userDelete(token);
    }

    //login with user id and password for token generation
    @PostMapping("/loginToken")
    public ResponseClass loginToken(@RequestParam String email, @RequestParam String password) {
        return iUserService.tokenGeneration(email, password);
    }
}
