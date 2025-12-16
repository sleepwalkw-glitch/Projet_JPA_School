package com.Exo.Projet.JPA_School.service;

import com.Exo.Projet.JPA_School.entity.Professeur;
import com.Exo.Projet.JPA_School.repository.ProfesseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProfesseurService {
    private final ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository){
        this.professeurRepository = professeurRepository;
    }

//E4_T1 Lister tous les professeurs
    public List<Professeur> getListProfesseurs() {
        return professeurRepository.findAll();
    }

//E4_T2 Obtenir un professeur par son ID
    public Optional<Professeur> getProfesseurById(Long id) {
        return professeurRepository.findById(id);
    }


//E4_T3 Créer un nouveau professeur
    public void createNewProfesseur(Professeur professeur) {
        professeurRepository.save(professeur);
}

//E4_T4 Modifier un professeur
    public void modifyProfesseur(Long id, Professeur professeur) {
        professeur.setId(id);
        professeurRepository.save(professeur);
    }

//E4_T5 Supprimer un professeur
    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }

}
