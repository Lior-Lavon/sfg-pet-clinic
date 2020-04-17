package guru.springframework.petclinicdata.mapImp;

import guru.springframework.petclinicdata.modules.Person;
import guru.springframework.petclinicdata.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonServiceMap extends AbstractMapService<Person, Long> implements CrudService<Person, Long> {

    @Override
    public Set<Person> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Person save(Person object) {
        return super.save(object);
    }

    @Override
    public void delete(Person object) {
        super.delete(object);
    }

    @Override
    public Person findById(Long id) {
        return findById(id);
    }
}
