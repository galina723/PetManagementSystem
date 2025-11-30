package org.example;

import org.example.model.Pet;
import org.example.repository.PetRepository;
import org.example.service.PetService;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PetService service = new PetService(new PetRepository());

        int choice;

        do {
            System.out.println("Pet Management Service");
            System.out.println("1. Add pet");
            System.out.println("2. Show all pets");
            System.out.println("3. Update pet");
            System.out.println("4. Delete pet");
            System.out.println("5. Exit");
            System.out.print("Choose the number from 1 to 5: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter integer number!");
                continue;
            }

            switch (choice) {
                case 1:
                    addPet(service);
                    break;
                case 2:
                    service.printAll();
                    break;
                case 3:
                    updatePet(service);
                    break;
                case 4:
                    deletePet(service);
                    break;
                case 5:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Your option is not valid");
            }

        } while (true);
    }

    private static void addPet(PetService service) {
        System.out.println("\nAdd pet");

        String id;
        do {
            System.out.print("Enter pet's id: ");

            id = sc.nextLine().trim();

            if (!service.validateId(id))
                System.out.println("Id must not be blank.");

            if (service.getById(id) != null)
                System.out.println("Id already exists.");
        } while (!service.validateId(id) || service.getById(id) != null);

        String name;
        do {
            System.out.print("Enter pet's name: ");
            name = sc.nextLine().trim();

            if (!service.validateName(name))
                System.out.println("Name must not be blank");
        } while (!service.validateName(name));

        int age = -1;

        System.out.print("Enter pet's age (>=0): ");

        String ageStr = sc.nextLine();

        age = Integer.parseInt(ageStr);

        String type;
        do {
            System.out.print("Enter type of pet: ");
            type = sc.nextLine().trim();

            if (!service.validateType(type))
                System.out.println("Type must not be blank.");
        } while (!service.validateType(type));

        Pet pet = new Pet(id, name, age, type);
        service.addPet(pet);

        System.out.println("Add successful.");
    }

    private static void updatePet(PetService service) {
        System.out.println("\nUpdate pet");
        System.out.print("Enter pet's id: ");
        String id = sc.nextLine().trim();

        Pet existing = service.getById(id);
        if (existing == null) {
            System.out.println("Cannot find pet.");
            return;
        }

        System.out.print("New name (Can use Enter button for skip): ");
        String name = sc.nextLine().trim();
        if (!name.isEmpty() && service.validateName(name))
            existing.setName(name);

        System.out.print("New age (Can use Enter button for skip): ");
        String ageStr = sc.nextLine().trim();

        if (!ageStr.isEmpty()) {
            if (!service.validateAgeFormat(ageStr)) {
                System.out.println("Please enter an integer number");
            } else {
                int age = Integer.parseInt(ageStr);
                existing.setAge(age);

            }
        }

        System.out.print("New type (Can use Enter button for skip): ");

        String type = sc.nextLine().trim();
        if (!type.isEmpty() && service.validateType(type))
            existing.setType(type);

        System.out.println("Update successful");
    }

    private static void deletePet(PetService service) {
        System.out.println("\nDelete pet");
        System.out.print("Enter pet's id: ");
        String id = sc.nextLine().trim();

        boolean deleted = service.deletePet(id);
        if (deleted)
            System.out.println("Delete successfully");
        else
            System.out.println("Cannot find pet");
    }
}
