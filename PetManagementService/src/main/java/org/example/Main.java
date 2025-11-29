package org.example;

import org.example.model.Pet;
import org.example.service.PetService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PetService service = new PetService();
        Scanner sc = new Scanner(System.in);

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
                    addPet(sc, service);
                    break;
                case 2:
                    service.printAll();
                    break;
                case 3:
                    updatePet(sc, service);
                    break;
                case 4:
                    deletePet(sc, service);
                    break;
                case 5:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Your option is not valid");
            }

        } while (true);
    }

    private static void addPet(Scanner sc, PetService service) {
        System.out.println("\nAdd pet");

        String id;
        do {
            System.out.print("Enter pet's id: ");

            id = sc.nextLine().trim();

            if (id.isEmpty())
                System.out.println("Id must not be blank.");
            if (service.getById(id) != null)
                System.out.println("Id already exists.");
        } while (id.isEmpty() || service.getById(id) != null);

        String name;
        do {
            System.out.print("Enter pet's name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty())
                System.out.println("Name must not be blank");
        } while (name.isEmpty());

        int age = -1;
        while (age < 0) {
            System.out.print("Enter pet's age (>=0): ");
            try {
                age = Integer.parseInt(sc.nextLine());
                if (age < 0)
                    System.out.println("Age must be equal or more than 0.");
            } catch (Exception e) {
                System.out.println("Please enter an integer number");
            }
        }

        String type;
        do {
            System.out.print("Enter type of pet: ");
            type = sc.nextLine().trim();
            if (type.isEmpty())
                System.out.println("Type must not be blank.");
        } while (type.isEmpty());

        Pet pet = new Pet(id, name, age, type);
        service.addPet(pet);

        System.out.println("Add successful.");
    }

    private static void updatePet(Scanner sc, PetService service) {
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
        if (!name.isEmpty())
            existing.setName(name);

        System.out.print("New age (Can use Enter button for skip): ");
        int age = -1;
        while (age < 0) {
            System.out.print("New age (Can use Enter button for skip): ");
            try {
                age = Integer.parseInt(sc.nextLine());
                if (age < 0)
                    System.out.println("Age must be equal or more than 0.");
            } catch (Exception e) {
                System.out.println("Please enter an integer number");
            }
        }

        System.out.print("New type (Can use Enter button for skip): ");
        String type = sc.nextLine().trim();
        if (!type.isEmpty())
            existing.setType(type);

        System.out.println("Update successful");
    }

    private static void deletePet(Scanner sc, PetService service) {
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
