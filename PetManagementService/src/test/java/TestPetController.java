import org.example.model.Pet;
import org.example.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPetController {

    private PetService service;

    @BeforeEach
    void setup() {
        service = new PetService();
    }

    @Test
    void testAddPet() {
        Pet p = new Pet("1", "Dog", 2, "Husky");
        assertTrue(service.addPet(p));
    }

    @Test
    void testAddPet_1() {
        service.addPet(new Pet("1", "Dog", 2, "Husky"));
        assertFalse(service.addPet(new Pet("1", "Cat", 3, "Meo")));
    }

    @Test
    void testAddPet_2() {
        Pet p = new Pet("2", "Wolf", -5, "Dog");
        assertFalse(service.addPet(p));
        assertEquals(-5, service.getById("2").getAge());
    }

    @Test
    void testGetAllPets() {
        assertEquals(0, service.count());
    }

    @Test
    void testGetAllPets_1() {
        service.addPet(new Pet("1", "Dog", 2, "Husky"));
        service.addPet(new Pet("2", "Cat", 3, "Meo"));

        assertEquals(2, service.count());
    }

    @Test
    void testGetAllPets_2() {
        service.addPet(new Pet("1", "Dog", 2, "Husky"));
        service.addPet(new Pet("2", "Cat", 3, "Meo"));

        assertEquals(3, service.count());
    }

    @Test
    void testUpdatePet() {
        service.addPet(new Pet("3", "Bird", 1, "Bird"));

        boolean ok = service.updatePet("3", "Bird2", -10, "BigBird");
        assertFalse(ok);
    }

    @Test
    void testUpdatePet_1() {
        service.addPet(new Pet("4", "Fish", 1, "Ca"));

        boolean ok = service.updatePet("4", "GoldFish", 3, "CaVang");
        assertTrue(ok);

        Pet p = service.getById("4");
        assertEquals("GoldFish", p.getName());
        assertEquals(3, p.getAge());
        assertEquals("CaVang", p.getType());
    }

    @Test
    void testDeletePet() {
        service.addPet(new Pet("5", "Dog", 5, "Husky"));
        assertTrue(service.deletePet("5"));
        assertNull(service.getById("5"));
    }

    @Test
    void testDeletePet_1() {
        assertFalse(service.deletePet("999"));
    }

    @Test
    void testDeletePet_2() {
        service.addPet(new Pet("5", "Dog", 5, "Husky"));
        assertTrue(service.deletePet("6"));
    }

    @Test
    void testGetById() {
        assertNull(service.getById("999"));
    }

    @Test
    void testGetById_1() {
        assertNotNull(service.getById("999"));
    }

    @Test
    void testCount() {
        assertEquals(0, service.count());
    }

    @Test
    void testCount_1() {
        assertEquals(2, service.count());
    }

    @Test
    void testCount_2() {
        service.addPet(new Pet("10", "A", 1, "T"));
        service.addPet(new Pet("20", "B", 2, "T"));
        assertEquals(2, service.count());
    }

    @Test
    void testCount_3() {
        service.addPet(new Pet("10", "A", 1, "T"));
        service.addPet(new Pet("20", "B", 2, "T"));
        assertEquals(5, service.count());
    }
}
