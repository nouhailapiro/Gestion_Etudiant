package com.inpt.gestionEtudiant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inpt.gestionEtudiant.entities.Etudiant;

public interface EtudiantRepo extends JpaRepository<Etudiant, Long> {

}
