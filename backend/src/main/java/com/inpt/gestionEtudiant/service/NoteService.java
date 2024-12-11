package com.inpt.gestionEtudiant.service;

import java.util.List;

import com.inpt.gestionEtudiant.dao.NoteDao;
import com.inpt.gestionEtudiant.entities.Note;

public interface NoteService {
  public Note addNote(NoteDao noteDao);

  public Note getNote(Long id);

  public List<Note> getNotesByEtudiantId(Long idEtudiant);

  public boolean deleteNote(Long id);

}
