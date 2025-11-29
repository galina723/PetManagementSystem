import org.example.model.Pet;
import org.example.service.PetService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPetController {

    // AddPet tests
    @Test
    void testAddPett() {
        PetService service = new PetService();
        Pet p = new Pet("1", "Doggo", 2, "dog");

        assertTrue(service.addPet(p));
        assertEquals(1, service.count());
    }

    @Test
    void testAddPet_1() {
        PetService service = new PetService();
        service.addPet(new Pet("1", "A", 2, "dog"));
        assertFalse(service.addPet(new Pet("1", "B", 3, "cat")));
    }

    // getById tests
    @Test
    void testGetById() {
        PetService service = new PetService();
        service.addPet(new Pet("1", "A", 1, "dog"));

        Pet p = service.getById("1");

        assertNotNull(p);
        assertEquals("A", p.getName());
    }

    @Test
    void testGetById_1() {
        PetService service = new PetService();
        assertNull(service.getById("999"));
    }

    @Test
    void testGetById_2() {
        PetService service = new PetService();
        service.addPet(new Pet("1", "A", 1, "dog"));

        assertThrows(NullPointerException.class, () -> {
            service.getById(null);
        });
    }

    // updatePet tests
    @Test
    void testUpdatePet() {
        PetService service = new PetService();
        service.addPet(new Pet("1", "A", 1, "dog"));

        boolean updated = service.updatePet("1", "NewName", 10, "cat");

        assertTrue(updated);

        Pet p = service.getById("1");
        assertEquals("NewName", p.getName());
        assertEquals(10, p.getAge());
        assertEquals("cat", p.getType());
    }

    @Test
    void testUpdatePet_1() {
        PetService service = new PetService();

        boolean result = service.updatePet("ABC", "X", 5, "cat");

        assertFalse(result);
    }

    @Test
    void testUpdatePet_2() {
        PetService service = new PetService();
        service.addPet(new Pet("1", "A", 1, "dog"));
        assertFalse(service.updatePet("1", "A", -2, "cat"));
    }

    // deletePet tests
    @Test
    void testDeletePet() {
        PetService service = new PetService();
        service.addPet(new Pet("1", "A", 1, "dog"));

        assertTrue(service.deletePet("1"));
        assertEquals(0, service.count());
    }

    @Test
    void testDeletePet_1() {
        PetService service = new PetService();
        assertFalse(service.deletePet("999"));
    }

    @Test
    void testDeletePet_2() {
        PetService service = new PetService();

        assertThrows(NullPointerException.class, () -> {
            service.deletePet(null);
        });
    }

    // count tests
    @Test
    void testCount() {
        PetService service = new PetService();
        service.addPet(new Pet("1", "A", 1, "dog"));
        service.addPet(new Pet("2", "B", 2, "cat"));

        service.deletePet("1");

        assertEquals(1, service.count());
    }
}
