package guru.springframework.petclinicdata.jpaImp.repositories;

import guru.springframework.petclinicdata.modules.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
