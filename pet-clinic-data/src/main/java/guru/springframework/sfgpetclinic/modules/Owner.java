package guru.springframework.sfgpetclinic.modules;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Entity
@Table(name = "owners")
public class Owner extends Person{

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public void setPet(Pet pet){
        pet.setOwner(this);
        this.pets.add(pet);
    }

    public void setPets(Set<Pet> pets) {
        pets.forEach(pet -> pet.setOwner(this));
        this.pets = pets;
    }
}
