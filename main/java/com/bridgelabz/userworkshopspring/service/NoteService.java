package com.bridgelabz.userworkshopspring.service;

import com.bridgelabz.userworkshopspring.dto.NoteDTO;
import com.bridgelabz.userworkshopspring.exception.UserNotFound;
import com.bridgelabz.userworkshopspring.model.NoteModel;
import com.bridgelabz.userworkshopspring.model.UserModel;
import com.bridgelabz.userworkshopspring.repository.INoteRepository;
import com.bridgelabz.userworkshopspring.repository.IUserRepository;
import com.bridgelabz.userworkshopspring.uitl.TokenUtil;
import org.apache.catalina.User;
import org.hibernate.service.NullServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements INoteService {

    @Autowired
    INoteRepository iNoteRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    IUserRepository iUserRepository;

    //creating new note with token as request header
    @Override
    public NoteModel newNote(String token, NoteDTO noteDTO) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            NoteModel noteModel = new NoteModel(noteDTO);
            noteModel.setCreatedDate(LocalDateTime.now());
            iNoteRepository.save(noteModel);
            return noteModel;
        }
        throw new UserNotFound(400, "User Not Found");
    }

    //updating note with token
    @Override
    public NoteModel editNote(String token, NoteDTO noteDTO) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            NoteModel noteModel = new NoteModel(noteDTO);
            noteModel.setUpdatedDate(LocalDateTime.now());
            iNoteRepository.save(noteModel);
            return noteModel;
        } else {
            throw new UserNotFound(400, "User not Found");
        }
    }

    //getting list
    @Override
    public List<NoteModel> viewList(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            List<NoteModel> getNoteList = iNoteRepository.findAll();
            if (getNoteList.size() > 0) {
                return getNoteList;
            } else {
                throw new UserNotFound(400, "User Not Found");
            }
        }
        throw new UserNotFound(400, "No Data Found");
    }

    //deleting list
    @Override
    public NoteModel removeNote(String token, Long noteID) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = iUserRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            Optional<NoteModel> isNotePresent = iNoteRepository.findByNoteId(noteID);
            iNoteRepository.delete(isNotePresent.get());
            return isNotePresent.get();
        } else {
            throw new UserNotFound(400, "No Note Found");
        }
    }
}
