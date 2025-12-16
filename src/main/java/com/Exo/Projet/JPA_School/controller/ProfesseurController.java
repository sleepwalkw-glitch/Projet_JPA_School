package com.Exo.Projet.JPA_School.controller;

import com.Exo.Projet.JPA_School.entity.Professeur;
import com.Exo.Projet.JPA_School.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/professeur")
public class ProfesseurController {
    private final ProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService){
        this.professeurService = professeurService;
    }

//E4_T1 Lister tous les professeurs
@GetMapping("/")
    public String getIndex(Model model){
    model.addAttribute("professeurs",professeurService.getListProfesseurs());
    return "professeur/index";
    }

//E4_T2 Obtenir un professeur par son ID
@GetMapping("/{id}")
    public String getProfesseurById(@PathVariable Long id, Model model) {
    Optional<Professeur> professeur = professeurService.getProfesseurById(id);
    if (professeur.isPresent()) {
        model.addAttribute("professeur", professeur.get());
        return "professeur/detail";
    }
    return "redirect:/professeur/";
    }

//E4_T3 Créer un nouveau professeur
    @GetMapping("/newProfesseur")
    public String newProfesseur(Model model){
        model.addAttribute("professeur",new Professeur());
        return "professeur/form";
    }

    @PostMapping("/newProfesseur")
    public String createNewProfesseur(@ModelAttribute Professeur professeur){
         professeurService.createNewProfesseur(professeur);
         return "redirect:/professeur/";
        }

//E4_T4 Modifier un professeur
    @GetMapping("/{id}/modify")
    public String modifyProfesseur(@PathVariable Long id, Model model){
    Optional<Professeur> professeur = professeurService.getProfesseurById(id);
    if (professeur.isPresent()){
        model.addAttribute("professeur",professeur.get());
        return "professeur/form";
    }
    return "redirect:/professeur/";
    }
    @PostMapping("{id}/modify")
    public String modifyProfesseur(@PathVariable Long id, @ModelAttribute Professeur professeur){
        professeurService.modifyProfesseur(id,professeur);
        return "redirect:/professeur/";
    }

//E4_T5 Supprimer un professeur
    @GetMapping("/{id}/delete")
    public String deleteProfesseur(@PathVariable Long id){
        Optional<Professeur> professeur = professeurService.getProfesseurById(id);
        if (professeur.isPresent()){
            professeurService.deleteProfesseur(id);
            return "redirect:/professeur/";
        }
        return "redirect:/professeur/";
    }
}


