package guru.springframework.petclinicdata.services;

import guru.springframework.petclinicdata.modules.Pet;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface PetService {

    public Pet findById(Long id);

    public Pet save(Pet vet);

    public Set<Pet> findAll();
}
