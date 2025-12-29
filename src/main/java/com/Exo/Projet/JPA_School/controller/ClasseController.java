package com.Exo.Projet.JPA_School.controller;

import com.Exo.Projet.JPA_School.entity.Classe;
import com.Exo.Projet.JPA_School.entity.Professeur;
import com.Exo.Projet.JPA_School.service.ClasseService;
import com.Exo.Projet.JPA_School.service.ProfesseurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/classe")
public class ClasseController {

    // here should use constructor for require the two Service layer , so that can send the demand for the two database,can use tha annotation @autowired instead of the constructor tapped manuelle
    private final ClasseService classeService;
    private final ProfesseurService professeurService;

    public ClasseController(ClasseService classeService, ProfesseurService professeurService){
        this.classeService=classeService;
        this.professeurService=professeurService;
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

    }   /*
    // GET /{id} -without optional
    @GetMapping("/{id}")
    public String voirClasse(@PathVariable Long id, Model model) {
        Classe classe = classeService.getClasseById(id);
        model.addAttribute("classe", classe);
        return "classe/detail";
    }
     */

//E4_T3 Créer un nouveau classe : GET then POST
    @GetMapping("/newClasse")
    public String newClasse(Model model){
        // to create a new object
        model.addAttribute("classe", new Classe());
        model.addAttribute("professeurs",professeurService.getListProfesseurs());
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
            model.addAttribute("professeurs",professeurService.getListProfesseurs());
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

    @GetMapping("/search")
    public String searchByNiveau(@RequestParam String niveau, Model model) {
        model.addAttribute("classes", classeService.findByNiveau(niveau));
        return "classe/index";
    }
    @GetMapping("/searchMatiere")
    public String searchByMatiere(@RequestParam String matiere, Model model) {
        model.addAttribute("classes", classeService.findByMatiere(matiere));
        return "classe/index";
    }
    // réassigner une classe à un autre professeur
    @GetMapping("/reassign/{id}")
    public String showReassignForm(@PathVariable Long id, Model model) {
        Classe classe = classeService.getClasseById(id).get();
        model.addAttribute("classe", classe);
        model.addAttribute("professeurs", professeurService.getListProfesseurs());
        return "classe/reassign";
    }
    @PostMapping("/reassign")
    public String reassignClasse(@RequestParam Long classeId, @RequestParam Long professeurId,
            RedirectAttributes redirectAttributes) {

        classeService.reassignProfesseur(classeId, professeurId);

        redirectAttributes.addFlashAttribute("message", "Classe réassignée avec succès !");
      return "redirect:/classe/";
    }
}
