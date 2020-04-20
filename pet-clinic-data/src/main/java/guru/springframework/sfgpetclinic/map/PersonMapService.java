package guru.springframework.sfgpetclinic.map;

import guru.springframework.sfgpetclinic.modules.Person;
import guru.springframework.sfgpetclinic.services.CrudService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PersonMapService extends AbstractMapServiceA<Person, Long> implements CrudService<Person, Long> {

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
