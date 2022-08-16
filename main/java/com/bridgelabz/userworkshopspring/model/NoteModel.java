package com.bridgelabz.userworkshopspring.model;

import com.bridgelabz.userworkshopspring.dto.NoteDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notes_reader_spring")
public class NoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long noteId;
    private String noteType;
    private String description;
    private String labels;
    private Long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public NoteModel(NoteDTO noteDTO) {
        this.noteType = noteDTO.getNoteType();
        this.description = noteDTO.getDescription();
        this.labels = noteDTO.getLabels();
        this.userId = noteDTO.getUserId();
    }

    public NoteModel() {
    }
}
