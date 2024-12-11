package com.inpt.gestionEtudiant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inpt.gestionEtudiant.entities.Etudiant;
import com.inpt.gestionEtudiant.entities.Note;

public interface NoteRepo extends JpaRepository<Note, Long> {
  List<Note> findByEtudiant(Etudiant etudiant);
}
