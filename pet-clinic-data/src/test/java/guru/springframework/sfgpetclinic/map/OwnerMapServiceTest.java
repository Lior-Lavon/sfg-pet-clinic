package guru.springframework.sfgpetclinic.map;

import guru.springframework.sfgpetclinic.modules.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        Owner o1 = new Owner();
        o1.setId(ownerId1);
        o1.setLastName(lastName);
        ownerMapService.save(o1);
    }

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

        Owner o2 = new Owner();
        o2.setId(ownerId2);

        Owner savedOwner = ownerMapService.save(o2);
        assertEquals(ownerId2, savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner o3 = new Owner();

        Owner savedOwner = ownerMapService.save(o3);

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

}