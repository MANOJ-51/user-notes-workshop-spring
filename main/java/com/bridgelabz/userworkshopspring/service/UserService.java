package com.bridgelabz.userworkshopspring.service;

import com.bridgelabz.userworkshopspring.dto.UserDTO;
import com.bridgelabz.userworkshopspring.exception.UserNotFound;
import com.bridgelabz.userworkshopspring.model.UserModel;
import com.bridgelabz.userworkshopspring.repository.IUserRepository;
import com.bridgelabz.userworkshopspring.uitl.ResponseClass;
import com.bridgelabz.userworkshopspring.uitl.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    //creating user
    @Override
    public UserModel userCreate(UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO);
        userModel.setRegisteredDate(LocalDateTime.now());
        iUserRepository.save(userModel);
        String body = "User Registration Is Successful with id :-" + userModel.getUserId() + "\n" + userDTO;
        String subject = " User Registration Success";
        mailService.send(userDTO.getEmailId(), body, subject);
        return userModel;
    }

    //updating user
    @Override
    public UserModel editUser(String token, UserDTO userDTO) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setFirstName(userDTO.getFirstName());
            isUserPresent.get().setLastName(userDTO.getLastName());
            isUserPresent.get().setEmailId(userDTO.getEmailId());
            isUserPresent.get().setPassword(userDTO.getPassword());
            isUserPresent.get().setUpdatedDate(LocalDateTime.now());
            iUserRepository.save(isUserPresent.get());
            String body = "User update Is Successful with id :-" + isUserPresent.get().getUserId() + "\n" + isUserPresent.get();
            String subject = " User update Success";
            mailService.send(isUserPresent.get().getEmailId(), body, subject);
            return isUserPresent.get();
        }
        throw new UserNotFound(400, "User Not Found");
    }

    //get list of user
    @Override
    public List<UserModel> viewList(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            List<UserModel> isDataPresent = iUserRepository.findAll();
            if (isDataPresent.size() > 0) {
                return isDataPresent;
            }
        }
        throw new UserNotFound(400, "No Data");
    }

    //delete user
    @Override
    public UserModel userDelete(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            iUserRepository.delete(isUserPresent.get());
            String body = "User  Registration Is Successful with id :-" + isUserPresent.get().getUserId() + "\n" + isUserPresent.get();
            String subject = " User Bet Registration Success";
            mailService.send(isUserPresent.get().getEmailId(), body, subject);
            return isUserPresent.get();
        }
        throw new UserNotFound(400, "User Not Found");
    }

    //token generation
    @Override
    public ResponseClass tokenGeneration(String email, String password) {
        Optional<UserModel> isEmailPresent = iUserRepository.findByEmailId(email);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getUserId());
                return new ResponseClass(200, "LoginSuccess", token);
            } else {
                throw new UserNotFound(400, "User password is Wrong");
            }
        }
        throw new UserNotFound(400, "No User Found");
    }
}
