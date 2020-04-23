package guru.springframework.sfgpetclinic.map;

import guru.springframework.sfgpetclinic.modules.Specialty;
import guru.springframework.sfgpetclinic.modules.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {

    VetService vetService;
    SpecialtyService specialtyService;

    final Long vetId1 = 1L;
    final Long vetId2 = 2L;

    @BeforeEach
    void setUp() {
        vetService = new VetMapService(specialtyService);

        // save demo data
        Vet vet = new Vet();
        vet.setId(vetId1);
        vet.setFirstName("lior");
        vet.setLastName("lavon");
        vetService.save(vet);

    }

    @Test
    void findAll() {
        Set<Vet> vets = vetService.findAll();
        assertEquals(1,  vets.size());
    }

    @Test
    void findById() {
        Vet vet = vetService.findById(vetId1);

        assertNotNull(vet);
        assertEquals(vetId1, vet.getId());
    }

    @Test
    void save() {

        Vet vet = new Vet();
        vet.setId(vetId2);
        Vet savedVet = vetService.save(vet);

        assertNotNull(savedVet);
        assertEquals(vetId2, vet.getId());
    }

    @Test
    void delete() {
        vetService.delete(vetService.findById(vetId1));
        assertEquals(0, vetService.findAll().size());
    }

    @Test
    void deleteById() {
    }
}