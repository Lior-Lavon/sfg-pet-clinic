package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.modules.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
