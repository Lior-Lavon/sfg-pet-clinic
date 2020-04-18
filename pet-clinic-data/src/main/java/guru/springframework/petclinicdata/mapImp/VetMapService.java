package guru.springframework.petclinicdata.mapImp;

import guru.springframework.petclinicdata.modules.Specialty;
import guru.springframework.petclinicdata.modules.Vet;
import guru.springframework.petclinicdata.services.SpecialtyService;
import guru.springframework.petclinicdata.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {

        if(vet != null && vet.getSpecialties() != null && vet.getSpecialties().size()>0){
            vet.getSpecialties().forEach(specialty ->{
                if(specialty.getId() == null)
                    throw new RuntimeException("Vet->Specialty id not set");
            });
        }

        return super.save(vet);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
