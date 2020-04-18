package guru.springframework.petdataweb.config;

import guru.springframework.petclinicdata.mapImp.*;
import guru.springframework.petclinicdata.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetClinicConfiguration {

    //    Init Bean Owner
    @Bean
    public OwnerService InitOwnerService(){
        return new OwnerMapService(InitPetTypeService(), new PetMapService());
    }

//    Init Bean Vet
    @Bean
    public VetService InitVetService(){
        return new VetMapService(InitSpecialtyService());
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

}
