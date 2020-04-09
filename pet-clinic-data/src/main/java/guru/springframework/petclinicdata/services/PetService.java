package guru.springframework.petclinicdata.services;

import guru.springframework.petclinicdata.modules.Vet;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface VetService {

    public Vet findById(Long id);

    public Vet save(Vet vet);

    public Set<Vet> findAll();
}
