package guru.springframework.petclinicdata.SDJpaImp.repositories;

import guru.springframework.petclinicdata.modules.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
