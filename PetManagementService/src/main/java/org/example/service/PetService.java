package org.example.service;

import org.example.model.Pet;
import org.example.repository.PetRepository;

public class PetService {

    private PetRepository repo = new PetRepository();

    public PetService(PetRepository repo) {
        this.repo = repo;
    }

    public boolean addPet(Pet pet) {
        if (repo.findById(pet.getId()) != null) {
            return false;
        }
        return repo.save(pet);
    }

    public Pet getById(String id) {
        return repo.findById(id);
    }

    public boolean updatePet(String id, String name, int age, String type) {
        Pet existing = repo.findById(id);
        if (existing == null)
            return false;

        existing.setName(name);
        existing.setAge(age);
        existing.setType(type);
        return true;
    }

    public boolean deletePet(String id) {
        if (repo.findById(id) == null)
            return false;

        return repo.delete(id);
    }

    public int count() {
        return repo.count();
    }

    public void printAll() {
        if (repo.count() == 0) {
            System.out.println("Pet list is empty.");
            return;
        }

        repo.findAll().forEach(System.out::println);
        System.out.println("Total pets: " + repo.count());
    }

    // ===========================
    // Validation
    // ===========================
    public boolean validateId(String id) {
        return id != null && !id.trim().isEmpty();
    }

    public boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public boolean validateType(String type) {
        return type != null && !type.trim().isEmpty();
    }

    public boolean validateAgeFormat(String ageStr) {
        try {
            Integer.parseInt(ageStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
