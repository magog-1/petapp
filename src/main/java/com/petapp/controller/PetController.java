package com.petapp.controller;

import com.petapp.model.Pet;
import com.petapp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    // Просмотр всех питомцев (доступно всем авторизованным)
    @GetMapping
    public String listPets(Model model) {
        model.addAttribute("pets", petService.getAllPets());
        return "pets";
    }

    // Добавление питомца (только ADMIN)
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addPet(@RequestParam String petName,
                        @RequestParam String species,
                        @RequestParam String ownerName) {
        Pet pet = new Pet();
        pet.setPetName(petName);
        pet.setSpecies(species);
        pet.setOwnerName(ownerName);
        petService.addPet(pet);
        return "redirect:/pets";
    }

    // Показать форму редактирования (только ADMIN)
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Pet> pet = petService.getPetById(id);
        if (pet.isPresent()) {
            model.addAttribute("pet", pet.get());
            return "edit-pet";
        }
        return "redirect:/pets";
    }

    // Обновление питомца (только ADMIN)
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updatePet(@PathVariable Long id, @ModelAttribute Pet pet) {
        pet.setId(id);
        petService.updatePet(pet);
        return "redirect:/pets";
    }

    // Удаление питомца (только ADMIN)
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return "redirect:/pets";
    }
}