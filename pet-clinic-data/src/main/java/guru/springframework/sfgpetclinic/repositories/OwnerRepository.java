package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.modules.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Implementing JPA SpringData Repositories

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
