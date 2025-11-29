package org.example.service;

import org.example.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetService {

    private final List<Pet> pets = new ArrayList<>();

    public boolean addPet(Pet pet) {
        if (exists(pet.getId())) {
            System.out.println("Pet ID already exists!");
            return false;
        }
        pets.add(pet);
        return true;
    }

    public boolean exists(String id) {
        return getById(id) != null;
    }

    public Pet getById(String id) {
        return pets.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean updatePet(String id, String name, int age, String type) {
        Pet existing = getById(id);
        if (existing == null)
            return false;

        existing.setName(name);
        existing.setAge(age);
        existing.setType(type);
        return true;
    }

    public boolean deletePet(String id) {
        if (!exists(id))
            return false;
        return pets.removeIf(p -> p.getId().equals(id));
    }

    public int count() {
        return pets.size();
    }

    public void printAll() {
        if (pets.isEmpty()) {
            System.out.println("Pet list is empty.");
            return;
        }

        pets.forEach(System.out::println);
        System.out.println("Total pets: " + count());
    }
}
