package org.example.repository;

import org.example.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    private final List<Pet> pets = new ArrayList<>();

    public boolean save(Pet pet) {
        return pets.add(pet);
    }

    public Pet findById(String id) {
        return pets.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean delete(String id) {
        return pets.removeIf(p -> p.getId().equals(id));
    }

    public List<Pet> findAll() {
        return pets;
    }

    public int count() {
        return pets.size();
    }
}
