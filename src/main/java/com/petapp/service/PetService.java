package com.petapp.service;

import com.petapp.model.Pet;
import com.petapp.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    // Получить все питомцы
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Получить питомца по ID
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    // Добавить нового питомца
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    // Обновить питомца
    public Pet updatePet(Pet pet) {
        return petRepository.save(pet);
    }

    // Удалить питомца
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    // Проверить существование питомца
    public boolean petExists(Long id) {
        return petRepository.existsById(id);
    }
}