package com.Exo.Projet.JPA_School.repository;

import com.Exo.Projet.JPA_School.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
}
