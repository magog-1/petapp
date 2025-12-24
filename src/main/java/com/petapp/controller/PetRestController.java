package com.petapp.controller;

import com.petapp.model.Pet;
import com.petapp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Pet> pet = petService.getPetById(id);
        return pet.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Создать нового питомца
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet savedPet = petService.addPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
    }

    // Обновить питомца
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        Optional<Pet> petOptional = petService.getPetById(id);
        
        if (!petOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Pet pet = petOptional.get();
        pet.setPetName(petDetails.getPetName());
        pet.setSpecies(petDetails.getSpecies());
        pet.setOwnerName(petDetails.getOwnerName());
        
        Pet updatedPet = petService.updatePet(pet);
        return ResponseEntity.ok(updatedPet);
    }

    // Удалить питомца
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        if (!petService.petExists(id)) {
            return ResponseEntity.notFound().build();
        }
        
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}