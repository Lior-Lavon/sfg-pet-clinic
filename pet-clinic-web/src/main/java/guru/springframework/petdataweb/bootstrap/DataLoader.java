package guru.springframework.petdataweb.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Mark as component -> this class becomes a Bean and get registered to the Spring context
// then becuse of the implementation, String is going to sun this Bean
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count==0)
            loadData();
    }

    private void loadData() {

        PetType dog = PetType.builder().name("Dog").build();
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = PetType.builder().name("Cat").build();
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded PetTypes...");

        Speciality radiology = Speciality.builder().description("Radiology").build();
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = Speciality.builder().description("Surgery").build();
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = Speciality.builder().description("Dentistry").build();
        Speciality savedDentistry = specialtyService.save(dentistry);

        System.out.println("Loaded Specialty...");

        Owner owner1 = Owner.builder().firstName("Michael").lastName("Weston").address("Jan Lienrstraat").city("Amsterdam").telephone("+31610776368").build();
        ownerService.save(owner1);

        Pet mikesPet = Pet.builder().petType(savedDogPetType).birthDate(LocalDate.now()).name("Rosco").owner(owner1).build();
        petService.save(mikesPet);

        owner1.getPets().add(mikesPet);

        Owner owner2 = Owner.builder().firstName("Fiona").lastName("Glenanne").address("Jan Lienrstraat").city("Amsterdam").telephone("+31610776368").build();
        ownerService.save(owner2);

        Pet fionasPet = Pet.builder().petType(savedCatPetType).birthDate(LocalDate.now()).name("Just-Cat").owner(owner2).build();
        petService.save(fionasPet);

        owner2.getPets().add(fionasPet);

        System.out.println("Loaded Owners...");

        Visit catVisit = Visit.builder().pet(fionasPet).date(LocalDate.now()).description("Sneezy Kitty").build();

        visitService.save(catVisit);

        Vet vet1 = Vet.builder().firstName("Sam").lastName("Axe").build();
        vet1.getSpecialities().add(savedRadiology);
        vet1.getSpecialities().add(savedDentistry);

        vetService.save(vet1);

        Vet vet2 = Vet.builder().firstName("Jessie").lastName("Porter").build();
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
