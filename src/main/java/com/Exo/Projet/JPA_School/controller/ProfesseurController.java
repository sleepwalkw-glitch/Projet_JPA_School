package com.Exo.Projet.JPA_School.controller;

import com.Exo.Projet.JPA_School.entity.Classe;
import com.Exo.Projet.JPA_School.entity.Professeur;
import com.Exo.Projet.JPA_School.service.ClasseService;
import com.Exo.Projet.JPA_School.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/professeur")
public class ProfesseurController {
    private final ProfesseurService professeurService;
    private final ClasseService classeService;

    public ProfesseurController(ProfesseurService professeurService, ClasseService classeService){
        this.professeurService = professeurService;
        this.classeService = classeService;
    }

//E4_T1 Lister tous les professeurs
@GetMapping("/")
    public String getIndex(Model model){
    List<Professeur> profs = professeurService.getListProfesseurs();
    model.addAttribute("professeurs",profs);
   // model.addAttribute("ProfesseursParAlphabetique",professeurService.getListProfesseursParAlphabetique());
    model.addAttribute("totalProfesseurs", professeurService.countProfesseurs());
    model.addAttribute("totalClasses", classeService.countClasses());
    //Add total students of every professor
    Map<Long, Integer> totalEleves = new HashMap<>();
    for (Professeur p : profs) {
        totalEleves.put(p.getId(), professeurService.getNombreEleves(p.getId()));
    }
    model.addAttribute("totalEleves", totalEleves);
    return "professeur/index";
    }

@GetMapping("/sorted")
    public String getSortedIndex(Model model){
        model.addAttribute("professeurs",professeurService.getListProfesseursParAlphabetique());
    model.addAttribute("totalProfesseurs", professeurService.countProfesseurs());
    model.addAttribute("totalClasses", classeService.countClasses());
        return "professeur/index";
    }

//E4_T2 Obtenir un professeur par son ID
@GetMapping("/{id}")
    public String getProfesseurById(@PathVariable Long id, Model model) {
    Optional<Professeur> professeur = professeurService.getProfesseurById(id);
    if (professeur.isPresent()) {
        model.addAttribute("professeur", professeur.get());
        model.addAttribute("nombreEleves",professeurService.getNombreEleves(id));
        model.addAttribute("nombreMoyenEleves", professeurService.getNombreMoyenEleves(id));
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
    public String createNewProfesseur(@ModelAttribute Professeur professeur ,RedirectAttributes redirectAttributes){
        if (!professeur.emailValide()){
        redirectAttributes.addFlashAttribute("message", "Error, Email invalide !");
            return "professeur/form";
        }
         professeurService.createNewProfesseur(professeur);
        redirectAttributes.addFlashAttribute("message", "Professeur créé avec succès !");
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
    public String modifyProfesseur(@PathVariable Long id, @ModelAttribute Professeur professeur, RedirectAttributes redirectAttributes){
        professeurService.modifyProfesseur(id,professeur);
        redirectAttributes.addFlashAttribute("message", "Professeur modifié avec succès !");
        return "redirect:/professeur/";
    }

//E4_T5 Supprimer un professeur
    @GetMapping("/{id}/delete")
    public String deleteProfesseur(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Optional<Professeur> professeur = professeurService.getProfesseurById(id);
        if (professeur.isPresent() && professeur.get().getClasses().isEmpty()){
            professeurService.deleteProfesseur(id);
            redirectAttributes.addFlashAttribute("message", "Professeur supprimé avec succès !");
            return "redirect:/professeur/";
        }
        redirectAttributes.addFlashAttribute("message", "Impossible de supprimer : ce professeur a des classes !");
        return "redirect:/professeur/";
    }
}


