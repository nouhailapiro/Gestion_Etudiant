package com.inpt.gestionEtudiant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inpt.gestionEtudiant.dao.NoteDao;
import com.inpt.gestionEtudiant.entities.Note;
import com.inpt.gestionEtudiant.service.NoteService;

@Controller
@RequestMapping("/api/notes")
public class NoteController {
  private final NoteService noteService;

  @Autowired
  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @PostMapping
  public ResponseEntity<Note> addNote(@RequestBody NoteDao noteDao) {
    Note note = noteService.addNote(noteDao);
    return new ResponseEntity<>(note, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Note> getNote(@PathVariable Long id) {
    Note note = noteService.getNote(id);
    if (note == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(note, HttpStatus.OK);
  }

  @GetMapping("/etudiant/{idEtudiant}")
  public ResponseEntity<List<Note>> getNotesByEtudiantId(@PathVariable Long idEtudiant) {
    List<Note> notes = noteService.getNotesByEtudiantId(idEtudiant);
    return new ResponseEntity<>(notes, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
    if (noteService.deleteNote(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
