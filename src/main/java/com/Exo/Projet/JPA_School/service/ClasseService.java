package com.Exo.Projet.JPA_School.service;

import com.Exo.Projet.JPA_School.entity.Classe;
import com.Exo.Projet.JPA_School.entity.Professeur;
import com.Exo.Projet.JPA_School.repository.ClasseRepository;
import com.Exo.Projet.JPA_School.repository.ProfesseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {
    private final ClasseRepository classeRepository;
    private final ProfesseurRepository professeurRepository;

    public ClasseService(ClasseRepository classeRepository, ProfesseurRepository professeurRepository) {
        this.classeRepository = classeRepository;
       this.professeurRepository = professeurRepository;

/* here we can create new object by adding the data to the repository
    professeurRepository.save(new Professeur("Dupont","Jean","jean.dupont@example.com"));
    professeurRepository.save(new Professeur("Martin","Sophie","sophie.martin@example.com"));
    professeurRepository.save(new Professeur("Durand","Paul","paul.durand@example.com"));

    classeRepository.save(new Classe("Classe A","Terminale","Mathématiques",30,prof1));
    classeRepository.save(new Classe("Classe B","Première","Histoire",25,prof1));
    classeRepository.save(new Classe("Classe C","Second","Physique",28,prof2));

    Classe classe1 = classeRepository.findById(1L).get();
    Classe classe2 = classeRepository.findById(2L).get();
    Classe classe3 = classeRepository.findById(3L).get();



    Professeur prof1 = professeurRepository.findById(1L).get();
    Professeur prof2 = professeurRepository.findById(2L).get();

 */
    }

//E4_T1 Lister tous les classes
    public List<Classe> getListClasses() {
        return classeRepository.findAll();}

//E4_T2 Obtenir un classe par son ID
    public Optional<Classe> getClasseById(Long id) {
        return classeRepository.findById(id);
    // here Classe can be Optional, what is the difference?
}

//E4_T3 Créer un nouveau classe
    public void createNewClasse(Classe classe) {
        classeRepository.save(classe);
}

//E4_T4 Modifier un classe
    public void modifyClasse(Long id, Classe classe) {
    classe.setId(id);
        classeRepository.save(classe);
}
//E4_T5 Supprimer un classe
    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
}

    public long countClasses() {
        return classeRepository.count();
    }

    public List<Classe> findByNiveau(String niveau) {
       return classeRepository.findByNiveauContainingIgnoreCase(niveau);
        }

    public List<Classe> findByMatiere(String matiere) {
        return classeRepository.findByMatiereContainingIgnoreCase(matiere);
    }

    public void reassignProfesseur(Long classeId, Long professeurId) {
        Classe classe = classeRepository.findById(classeId).orElseThrow();
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow();
        classe.setProfesseur(professeur);
        classeRepository.save(classe);
    }
}





