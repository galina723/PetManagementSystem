package org.example.model;

public class Pet {
    private String id;
    private String name;
    private int age;
    private String type;

    public Pet(String id, String name, int age, String type) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getType() { return type; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setType(String type) { this.type = type; }
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Age: %d | Type: %s",
                id, name, age, type);
    }
}
