package guru.springframework.petclinicdata.jpaImp.repositories;

import guru.springframework.petclinicdata.modules.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// Implementing JPA SpringData Repositories
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
