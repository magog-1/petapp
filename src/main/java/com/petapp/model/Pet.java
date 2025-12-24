package com.petapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "species", nullable = false)
    private String species;

    // ✅ Добавьте конструкторы и методы
    public Pet() {}

    public Pet(Long id, String ownerName, String petName, String species) {
        this.id = id;
        this.ownerName = ownerName;
        this.petName = petName;
        this.species = species;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
}