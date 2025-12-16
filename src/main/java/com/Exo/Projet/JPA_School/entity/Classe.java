package com.Exo.Projet.JPA_School.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String niveau;
    private String matiere;
    private int nombreEleves;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    @JsonBackReference
    private Professeur professeur;

    public Classe() {}

    public Classe(final String nom,final String niveau,final String matiere,final int nombreEleves,final Professeur professeur) {
        this.nom = nom;
        this.niveau = niveau;
        this.matiere = matiere;
        this.nombreEleves = nombreEleves;
        this.professeur = professeur;
    }

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getNiveau() {return niveau;}

    public void setNiveau(String niveau) {this.niveau = niveau;}

    public String getMatiere() {return matiere;}

    public void setMatiere(String matiere) {this.matiere = matiere;}

    public int getNombreEleves() {return nombreEleves;}

    public void setNombreEleves(int nombreEleves) {this.nombreEleves = nombreEleves;}

    public Professeur getProfesseur() {return professeur;}

    public void setProfesseur(Professeur professeur) {this.professeur = professeur;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}
}
