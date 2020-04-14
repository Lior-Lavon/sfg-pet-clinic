package guru.springframework.petclinicdata.modules;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Pet extends BaseEntity{

    private PetType petType;
    private String name;
    private Owner owner;
    private LocalDate birthDate;

    public Pet() {
    }

    public Pet(PetType petType, String name, Owner owner, LocalDate birthDate) {
        this.petType = petType;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
