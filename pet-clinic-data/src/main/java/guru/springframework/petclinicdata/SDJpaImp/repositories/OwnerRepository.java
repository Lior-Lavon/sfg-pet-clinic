package guru.springframework.petclinicdata.SDJpaImp.repositories;

import guru.springframework.petclinicdata.modules.Owner;
import org.springframework.data.repository.CrudRepository;

// Implementing JPA SpringData Repositories
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
