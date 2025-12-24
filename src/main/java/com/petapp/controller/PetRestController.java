package com.petapp.controller;

import com.petapp.model.Pet;
import com.petapp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*") // Разрешаем доступ из JavaFX приложения
public class PetRestController {

    @Autowired
    private PetService petService;

    // Получить всех питомцев
    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    // Получить питомца по ID
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            return ResponseEntity.ok(pet);
        }
        return ResponseEntity.notFound().build();
    }

    // Создать нового питомца
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet savedPet = petService.savePet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
    }

    // Обновить питомца
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        Pet pet = petService.getPetById(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        
        pet.setName(petDetails.getName());
        pet.setSpecies(petDetails.getSpecies());
        pet.setAge(petDetails.getAge());
        pet.setOwner(petDetails.getOwner());
        
        Pet updatedPet = petService.savePet(pet);
        return ResponseEntity.ok(updatedPet);
    }

    // Удалить питомца
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}