package guru.springframework.petdataweb.config;

import guru.springframework.petclinicdata.services.OwnerService;
import guru.springframework.petclinicdata.services.PetTypeService;
import guru.springframework.petclinicdata.services.SpecialtyService;
import guru.springframework.petclinicdata.services.VetService;
import guru.springframework.petclinicdata.services.map.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetClinicConfiguration {

    //    Init Bean Owner
    @Bean
    public OwnerService InitOwnerService(){
        return new OwnerServiceMap(InitPetTypeService(), new PetServiceMap());
    }

//    Init Bean Vet
    @Bean
    public VetService InitVetService(){
        return new VetServiceMap(InitSpecialtyService());
    }

// Init Bean PetType
    @Bean
    public PetTypeService InitPetTypeService(){
        return new PetTypeServiceMap();
    }

// Init Bean Specialty
    @Bean
    public SpecialtyService InitSpecialtyService(){
        return new SpecialtyServiceMap();
    }


}
