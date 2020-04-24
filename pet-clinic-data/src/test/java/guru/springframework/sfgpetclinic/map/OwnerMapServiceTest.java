package guru.springframework.sfgpetclinic.map;

import guru.springframework.sfgpetclinic.modules.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Test with junit-5.0
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId1 = 1L;
    final Long ownerId2 = 2L;

    final String lastName = "lavon";

    /// this will run before each test
    @BeforeEach
    void setUp() {

        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        // set init data
        ownerMapService.save(Owner.builder().id(ownerId1).lastName(lastName).build());
    }
/*
    @Test
    void findAll() {

        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId1);
        assertEquals(ownerId1, owner.getId());
    }

    @Test
    void saveExistingId() {

        Owner savedOwner = ownerMapService.save(Owner.builder().id(ownerId2).build());
        assertEquals(ownerId2, savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId1));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {

        ownerMapService.deleteById(ownerId1);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("foo");

        assertNull(owner);
    }

 */
}