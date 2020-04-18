package guru.springframework.petclinicdata.repositories;

import guru.springframework.petclinicdata.modules.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
