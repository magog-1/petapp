package com.petapp.client.model;

public class Pet {
    private Long id;
    private String name;
    private String species;
    private Integer age;
    private String owner;

    public Pet() {}

    public Pet(String name, String species, Integer age, String owner) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.owner = owner;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    @Override
    public String toString() {
        return "Pet{id=" + id + ", name='" + name + "', species='" + species + 
               "', age=" + age + ", owner='" + owner + "'}";
    }
}