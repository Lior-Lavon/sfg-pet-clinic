package guru.springframework.sfgpetclinic.map;

import guru.springframework.sfgpetclinic.modules.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapServiceA<Owner, Long> implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {

        if(owner != null && owner.getPets() != null && owner.getPets().size()>0){
            owner.getPets().forEach(pet -> {
                if(pet.getId() == null)
                    throw new RuntimeException("pet id not exist");
                if(pet.getPetType().getId() == null)
                    throw new RuntimeException("petType id not exist");
            });
        }
        return super.save(owner);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
