package com.petapp.client.model;

public class Pet {
    private Long id;
    private String petName;
    private String species;
    private String ownerName;

    public Pet() {}

    public Pet(String petName, String species, String ownerName) {
        this.petName = petName;
        this.species = species;
        this.ownerName = ownerName;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    @Override
    public String toString() {
        return "Pet{id=" + id + ", petName='" + petName + "', species='" + species + 
               "', ownerName='" + ownerName + "'}";
    }
}