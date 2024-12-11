package com.inpt.gestionEtudiant.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inpt.gestionEtudiant.dao.EtudiantDao;
import com.inpt.gestionEtudiant.entities.Etudiant;
import com.inpt.gestionEtudiant.repositories.EtudiantRepo;

@Service
public class EtudiantServiceImpl implements EtudiantService {

  private EtudiantRepo etudiantRepo;

  @Autowired
  public EtudiantServiceImpl(EtudiantRepo etudiantRepo) {
    this.etudiantRepo = etudiantRepo;
  }

  @Override
  public Etudiant createEtudiant(EtudiantDao etudiantDao) {
    Etudiant etudiant = Etudiant.builder()
        .nom(etudiantDao.getNom())
        .dateCreation(LocalDate.now())
        .build();
    return etudiantRepo.save(etudiant);
  }

  @Override
  public Etudiant getEtudiant(Long id) {
    return etudiantRepo.findById(id).orElse(null);
  }

  @Override
  public List<Etudiant> getAllEtudiant() {
    return etudiantRepo.findAll();
  }

  @Override
  public boolean deleteEtudiant(Long id) {
    if (etudiantRepo.existsById(id)) {
      etudiantRepo.deleteById(id);
      return true;
    }
    return false;
  }
}
