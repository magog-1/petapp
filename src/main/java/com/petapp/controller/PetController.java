package com.petapp.controller;

import com.petapp.model.Pet;
import com.petapp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public String listPets(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "index";
    }

    @GetMapping("/add")
    public String showAddPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "add-pet";
    }

    @PostMapping("/save")
    public String savePet(@ModelAttribute Pet pet) {
        petService.addPet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/edit/{id}")
    public String showEditPetForm(@PathVariable Long id, Model model) {
        Optional<Pet> pet = petService.getPetById(id);
        if (pet.isPresent()) {
            model.addAttribute("pet", pet.get());
            return "edit-pet";
        }
        return "redirect:/pets";
    }

    @PostMapping("/update/{id}")
    public String updatePet(@PathVariable Long id, @ModelAttribute Pet pet) {
        pet.setId(id);
        petService.updatePet(pet);
        return "redirect:/pets";
    }

    @PostMapping("/delete/{id}")
    public String deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return "redirect:/pets";
    }
}