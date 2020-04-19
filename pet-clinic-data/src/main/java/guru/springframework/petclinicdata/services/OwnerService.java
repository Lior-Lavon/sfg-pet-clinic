package guru.springframework.petclinicdata.services;

import guru.springframework.petclinicdata.modules.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
