package com.inpt.gestionEtudiant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inpt.gestionEtudiant.dao.NoteDao;
import com.inpt.gestionEtudiant.entities.Etudiant;
import com.inpt.gestionEtudiant.entities.Note;
import com.inpt.gestionEtudiant.repositories.EtudiantRepo;
import com.inpt.gestionEtudiant.repositories.NoteRepo;

@Service
public class NoteServiceImpl implements NoteService {

  private NoteRepo noteRepo;
  private EtudiantRepo etudiantRepo;

  @Autowired
  public NoteServiceImpl(NoteRepo noteRepo, EtudiantRepo etudiantRepo) {
    this.etudiantRepo = etudiantRepo;
    this.noteRepo = noteRepo;
  }

  @Override
  public Note addNote(NoteDao noteDao) {
    Etudiant etudiant = etudiantRepo.findById(noteDao.getIdEtudiant())
        .orElseThrow(() -> new IllegalArgumentException("Étudiant introuvable avec l'ID : " + noteDao.getIdEtudiant()));

    Note note = Note.builder()
        .etudiant(etudiant)
        .nomCours(noteDao.getNomCours())
        .valeurNote(noteDao.getNoteCours())
        .build();

    return noteRepo.save(note);
  }

  @Override
  public Note getNote(Long id) {
    return noteRepo.findById(id).orElse(null);
  }

  @Override
  public List<Note> getNotesByEtudiantId(Long idEtudiant) {
    Etudiant etudiant = etudiantRepo.findById(idEtudiant)
        .orElseThrow(() -> new IllegalArgumentException("Étudiant introuvable avec l'ID : " + idEtudiant));
    return noteRepo.findByEtudiant(etudiant);
  }

  @Override
  public boolean deleteNote(Long id) {
    if (noteRepo.existsById(id)) {
      noteRepo.deleteById(id);
      return true;
    }
    return false;
  }
}
