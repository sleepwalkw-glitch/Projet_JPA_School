package com.Exo.Projet.JPA_School.service;

import com.Exo.Projet.JPA_School.entity.Classe;
import com.Exo.Projet.JPA_School.entity.Professeur;
import com.Exo.Projet.JPA_School.repository.ProfesseurRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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

    public List<Professeur> getListProfesseursParAlphabetique() {
        List<Professeur> professeurs = professeurRepository.findAll();
        professeurs.sort(new Comparator<Professeur>() {
            @Override
            public int compare(Professeur p1, Professeur p2) {
                String nomComplet1 = p1.getNom() + " " + p1.getPrenom();
                String nomComplet2 = p2.getNom() + " " + p2.getPrenom();
                return nomComplet1.compareToIgnoreCase(nomComplet2);
            }
        });
        return professeurs;
    }

//E4_T2 Obtenir un professeur par son ID
    public Optional<Professeur> getProfesseurById(Long id) {
        return professeurRepository.findById(id);
    }

//E6-T3 numbre total d'eleves
    public int getNombreEleves(Long id) {
        Professeur professeur= professeurRepository.findById(id).orElse(null);
            if (professeur == null) {
                return 0;
            } else {
                int total = 0;
                for (Classe c : professeur.getClasses()) {
                    total += c.getNombreEleves();
                }
                return total;
            }
        }

    public int getNombreMoyenEleves(Long id) {
        Professeur professeur= professeurRepository.findById(id).orElse(null);
        if (professeur == null || professeur.getClasses().isEmpty()){
            return 0;
        }
        else {
            return (int)(getNombreEleves(id)/professeur.getClasses().size());
        }
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


    public long countProfesseurs() {
        return professeurRepository.count();
    }
}
