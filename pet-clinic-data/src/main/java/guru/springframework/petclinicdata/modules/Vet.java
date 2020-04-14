package guru.springframework.petclinicdata.modules;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Vet extends Person {

    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
