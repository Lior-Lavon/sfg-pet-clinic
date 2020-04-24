package guru.springframework.sfgpetclinic.map;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestUser {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
}
