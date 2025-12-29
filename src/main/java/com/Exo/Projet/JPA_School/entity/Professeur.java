package com.Exo.Projet.JPA_School.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;

    private String email;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<Classe> classes = new ArrayList<>();

    public Professeur() {}

    public Professeur(final String nom,final String prenom,final String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Professeur(List<Classe> classes) {
        this.classes = classes;
    }

    public boolean emailValide() {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }
}
