package com.Exo.Projet.JPA_School.controller;

import com.Exo.Projet.JPA_School.entity.Classe;
import com.Exo.Projet.JPA_School.service.ClasseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/classe")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService){
        this.classeService=classeService;
    }

//E4_T1 Lister tous les classes
    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("classes",classeService.getListClasses());
        return "classe/index";
    }

//E4_T2 Obtenir un classe par son ID
    @GetMapping("/{id}")
    public  String getClasseById(@PathVariable Long id, Model model){
        Optional<Classe> classe = classeService.getClasseById(id);
        if (classe.isPresent()){
            model.addAttribute("classe",classe.get());
            return "classe/detail";
        }
        return "redirect:/classe/";// if the id is not exist, return to the home page

    }

    /*
    // GET /{id} -without optional
    @GetMapping("/{id}")
    public String voirClasse(@PathVariable Long id, Model model) {
        Classe classe = classeService.getClasseById(id);
        model.addAttribute("classe", classe);
        return "classe/detail";
    }
     */

//E4_T3 Créer un nouveau classe
    @GetMapping("/newClasse")
    public String newClasse(Model model){
        model.addAttribute("classe", new Classe());
        return "classe/form";
    }

    @PostMapping("/newClasse")
    public String createNewClasse(@ModelAttribute Classe classe){
        classeService.createNewClasse(classe);
        return "redirect:/classe/";
    }
//E4_T4 Modifier un classe
    @GetMapping("/{id}/modify")
    public String modifyClasse(@PathVariable Long id, Model model){
        Optional<Classe> classe = classeService.getClasseById(id);
        if (classe.isPresent()){
            model.addAttribute("classe",classe.get());
            return "classe/form";
        }
        return "redirect:/classe/";// if the id is not exist, return to home page
    }

    @PostMapping("/{id}/modify")
    public String modifyClasse(@PathVariable Long id, @ModelAttribute Classe classe){
        classeService.modifyClasse(id,classe);
        return "redirect:/classe/";
    }
//E4_T5 Supprimer un classe
    @GetMapping("/{id}/delete")
    public String deleteClasse(@PathVariable Long id){
        Optional<Classe> classe = classeService.getClasseById(id);
        if (classe.isPresent()){
            classeService.deleteClasse(id);
            return "redirect:/classe/";
        }
        return "redirect:/classe/";
    }

}
