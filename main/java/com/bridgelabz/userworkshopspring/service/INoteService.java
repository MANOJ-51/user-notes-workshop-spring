package com.bridgelabz.userworkshopspring.service;

import com.bridgelabz.userworkshopspring.dto.NoteDTO;
import com.bridgelabz.userworkshopspring.model.NoteModel;

import java.util.List;

public interface INoteService {
    NoteModel newNote(String token, NoteDTO noteDTO);

    NoteModel editNote(String token, NoteDTO noteDTO);

    List<NoteModel> viewList(String token);

    NoteModel removeNote(String token,Long noteId);
}
