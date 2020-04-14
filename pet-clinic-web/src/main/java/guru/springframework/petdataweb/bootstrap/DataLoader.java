package guru.springframework.petdataweb.bootstrap;

import guru.springframework.petclinicdata.modules.Owner;
import guru.springframework.petclinicdata.modules.Pet;
import guru.springframework.petclinicdata.modules.PetType;
import guru.springframework.petclinicdata.modules.Vet;
import guru.springframework.petclinicdata.services.OwnerService;
import guru.springframework.petclinicdata.services.PetTypeService;
import guru.springframework.petclinicdata.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// Mark as component -> this class becomes a Bean and get registered to the Spring context
// then becaose of the implementation, String is going to sun this Bean
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

//        System.out.println("Loaded PetTypes...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("Jan Lienrstraat");
        owner1.setCity("Amsterdam");
        owner1.setTelephone("+31610776368");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        mikesPet.setOwner(owner1);

        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("Jan Lienrstraat");
        owner2.setCity("Amsterdam");
        owner2.setTelephone("+31610776368");

        Pet fionasPet = new Pet();
        fionasPet.setPetType(savedCatPetType);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setName("Just-Cat");
        fionasPet.setOwner(owner2);

        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
