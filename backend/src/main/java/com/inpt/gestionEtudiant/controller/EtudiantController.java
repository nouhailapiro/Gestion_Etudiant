package com.inpt.gestionEtudiant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inpt.gestionEtudiant.dao.EtudiantDao;
import com.inpt.gestionEtudiant.entities.Etudiant;
import com.inpt.gestionEtudiant.service.EtudiantService;

@Controller
// @CrossOrigin(origins = "*")
@RequestMapping("/api/etudiants")
public class EtudiantController {
  private final EtudiantService etudiantService;

  @Autowired
  public EtudiantController(EtudiantService etudiantService) {
    this.etudiantService = etudiantService;
  }

  @PostMapping
  public ResponseEntity<Etudiant> createEtudiant(@RequestBody EtudiantDao etudiantDao) {
    Etudiant etudiant = etudiantService.createEtudiant(etudiantDao);
    return new ResponseEntity<>(etudiant, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Etudiant> getEtudiant(@PathVariable Long id) {
    Etudiant etudiant = etudiantService.getEtudiant(id);
    if (etudiant == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(etudiant, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Etudiant>> getAllEtudiants() {
    List<Etudiant> etudiants = etudiantService.getAllEtudiant();
    return new ResponseEntity<>(etudiants, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
    if (etudiantService.deleteEtudiant(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
