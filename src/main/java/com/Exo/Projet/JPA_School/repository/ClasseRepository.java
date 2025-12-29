package com.Exo.Projet.JPA_School.repository;

import com.Exo.Projet.JPA_School.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    List<Classe> findByNiveauContainingIgnoreCase(String niveau);

    List<Classe> findByMatiereContainingIgnoreCase(String matiere);
}
