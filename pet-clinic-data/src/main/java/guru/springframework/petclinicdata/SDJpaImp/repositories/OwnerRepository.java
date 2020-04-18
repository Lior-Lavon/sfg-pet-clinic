package guru.springframework.petclinicdata.SDJpaImp.repositories;

import guru.springframework.petclinicdata.modules.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Implementing JPA SpringData Repositories
@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
