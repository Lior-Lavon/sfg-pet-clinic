package guru.springframework.petdataweb.config;

import guru.springframework.petclinicdata.mapImp.*;
import guru.springframework.petclinicdata.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"default", "map"})
public class PetClinicMapConfiguration {

//    Init Bean Owner
    @Bean
    public OwnerService InitOwnerService(){
        return new OwnerMapService();
    }

//    Init Bean Vet
    @Bean
    public VetService InitVetService(){
        return new VetMapService();
    }

// Init Bean PetType
    @Bean
    public PetTypeService InitPetTypeService(){
        return new PetTypeMapService();
    }

// Init Bean Specialty
    @Bean
    public SpecialtyService InitSpecialtyService(){
        return new SpecialtyMapService();
    }

// Init Bean Visit
    @Bean
    public VisitService InitVisitService(){
        return new VisitMapService();
    }

// Init Bean Pet
    @Bean
    public PetService InitPetService(){
        return new PetMapService();
    }

}
