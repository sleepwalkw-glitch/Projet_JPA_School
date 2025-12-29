package com.Exo.Projet.JPA_School;

import com.Exo.Projet.JPA_School.entity.Classe;
import com.Exo.Projet.JPA_School.entity.Professeur;
import com.Exo.Projet.JPA_School.repository.ClasseRepository;
import com.Exo.Projet.JPA_School.repository.ProfesseurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@SpringBootApplication
public class ProjetJpaSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetJpaSchoolApplication.class, args);}

        @Bean
        CommandLineRunner commandLineRunner(ClasseRepository  classeRepository,
        ProfesseurRepository professeurRepository) {
            return args -> {
                professeurRepository.save(new Professeur("Dupont", "Jean", "jean.dupont@example.com"));
                professeurRepository.save(new Professeur("Martin", "Sophie", "sophie.martin@example.com"));
                professeurRepository.save(new Professeur("Durand", "Paul", "paul.durand@example.com"));

               Professeur prof1 = professeurRepository.findById(1L).get();
               Professeur prof2 = professeurRepository.findById(2L).get();
               Professeur prof3 = professeurRepository.findById(3L).get();

                classeRepository.save(new Classe("Classe A", "Terminale", "Mathématiques", 30, prof1,"RDC101"));
                classeRepository.save(new Classe("Classe B", "Première", "Histoire", 25, prof2, "RDC102"));
                classeRepository.save(new Classe("Classe C", "Second", "Physique", 28, prof3, "RDC103"));

                Classe classe1 = classeRepository.findById(1L).get();
                Classe classe2 = classeRepository.findById(2L).get();
                Classe classe3 = classeRepository.findById(3L).get();

            };
        }
	}



