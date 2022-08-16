package com.bridgelabz.userworkshopspring.controller;

import com.bridgelabz.userworkshopspring.dto.NoteDTO;
import com.bridgelabz.userworkshopspring.model.NoteModel;
import com.bridgelabz.userworkshopspring.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noteHome")
public class NoteController {

    @Autowired
    INoteService iNoteService;

    //welcome note
    @GetMapping("/noteWelcome")
    public String welcomeMessage() {
        return "Welcome to Notes Spring";
    }

    //creating note with token as request header
    @PostMapping("/createNote")
    public NoteModel createNote(@RequestHeader String token, @RequestBody NoteDTO noteDTO) {
        return iNoteService.newNote(token, noteDTO);
    }

    //Updating note
    @PutMapping("/updateNote")
    public NoteModel updateNote(@RequestHeader String token, @RequestBody NoteDTO noteDTO) {
        return iNoteService.editNote(token, noteDTO);
    }

    //retrieving note
    @GetMapping("/getNoteList")
    public List<NoteModel> getList(@RequestHeader String token) {
        return iNoteService.viewList(token);
    }

    //delete note
    @DeleteMapping("/deleteNote/{noteId}")
    public NoteModel deleteNote(@RequestHeader String token, @PathVariable Long noteId) {
        return iNoteService.removeNote(token, noteId);
    }
}
