package guru.springframework.sfgpetclinic.modules;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
//@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{

    @Builder
    public Owner(Long id, String firstName, String lastName, String address,
                 String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if(pets != null){
            this.pets = pets;
        }
    }

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

    public void setId(Long id){
        super.setId(id);
    }
}
