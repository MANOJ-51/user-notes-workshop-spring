package com.bridgelabz.userworkshopspring.repository;

import com.bridgelabz.userworkshopspring.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface INoteRepository extends JpaRepository<NoteModel, Long> {
    Optional<NoteModel> findByNoteId(Long userId);
}
