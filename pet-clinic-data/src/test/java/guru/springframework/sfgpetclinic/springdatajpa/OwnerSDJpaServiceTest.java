package guru.springframework.sfgpetclinic.springdatajpa;

import guru.springframework.sfgpetclinic.modules.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// mockito test example
@ExtendWith(MockitoExtension.class) // this setup the junit-5.0 Mockito in the environment
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks        // init the service with all repositories
    OwnerSDJpaService ownerSDJpaService;

    final String FIRST_NAME = "lior";
    final Long ownerId1 = 1L;
    final Long ownerId2 = 2L;

    Owner onr ;

    @BeforeEach
    void setUp() {
        // ownerSDJpaService = new OwnerSDJpaService(ownerRepository, petRepository, petTypeRepository);
        onr = Owner.builder().id(ownerId1).firstName(FIRST_NAME).build();
    }

    @Test
    void findByLastName() {

        // when the ownerRepository.findByLastName is called -> emulate the return of the object returnOwner
        when(ownerRepository.findByLastName(any())).thenReturn(onr);

        Owner owner = ownerSDJpaService.findByLastName(FIRST_NAME);

        assertNotNull(owner);
        assertEquals(FIRST_NAME, owner.getFirstName());
    }

    @Test
    void findAll() {

        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(ownerId1).build());
        returnOwnerSet.add(Owner.builder().id(ownerId2).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(onr));

        Owner owner = ownerSDJpaService.findById(ownerId1);

        assertNotNull(owner);
        assertEquals(ownerId1, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        Owner owner = ownerSDJpaService.findById(ownerId1);

        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(onr);

        Owner owner = ownerSDJpaService.save(onr);

        assertNotNull(owner);
        assertEquals(ownerId1, owner.getId());
    }

    @Test
    void delete() {

        ownerSDJpaService.delete(onr);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(ownerId1);

        verify(ownerRepository, times(1)).deleteById(any());
    }
}