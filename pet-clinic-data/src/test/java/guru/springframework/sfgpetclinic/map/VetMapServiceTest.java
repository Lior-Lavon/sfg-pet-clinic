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
        vetService.save(Vet.builder().id(vetId1).firstName("lior").lastName("lavon").build());

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

        Vet savedVet = vetService.save(Vet.builder().id(vetId2).build());

        assertNotNull(savedVet);
        assertEquals(vetId2, savedVet.getId());
    }

    @Test
    void delete() {
        vetService.delete(vetService.findById(vetId1));
        assertEquals(0, vetService.findAll().size());
    }

    @Test
    void deleteById() {
        vetService.deleteById(vetId1);
        assertEquals(0, vetService.findAll().size());

    }
}