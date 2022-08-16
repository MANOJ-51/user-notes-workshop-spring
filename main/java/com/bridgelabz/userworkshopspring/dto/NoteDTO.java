package com.bridgelabz.userworkshopspring.dto;

import lombok.Data;

@Data
public class NoteDTO {
    private Long noteId;
    private String noteType;
    private String description;
    private String labels;
    private Long userId;
}
