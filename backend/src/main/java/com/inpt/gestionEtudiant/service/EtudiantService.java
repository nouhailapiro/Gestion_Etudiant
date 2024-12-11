package com.inpt.gestionEtudiant.service;

import java.util.List;

import com.inpt.gestionEtudiant.dao.EtudiantDao;
import com.inpt.gestionEtudiant.entities.Etudiant;

public interface EtudiantService {
  public Etudiant createEtudiant(EtudiantDao etudiantDao);

  public Etudiant getEtudiant(Long id);

  public List<Etudiant> getAllEtudiant();

  public boolean deleteEtudiant(Long id);
}
