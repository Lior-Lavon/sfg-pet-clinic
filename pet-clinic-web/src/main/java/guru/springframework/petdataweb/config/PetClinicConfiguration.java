package guru.springframework.petdataweb.config;

import guru.springframework.petclinicdata.services.OwnerService;
import guru.springframework.petclinicdata.services.PetTypeService;
import guru.springframework.petclinicdata.services.VetService;
import guru.springframework.petclinicdata.services.map.OwnerServiceMap;
import guru.springframework.petclinicdata.services.map.PetTypeServiceMap;
import guru.springframework.petclinicdata.services.map.VetServiceMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetClinicConfiguration {

//    Init Bean Owner
    @Bean
    public OwnerService InitOwnerService(){
        return new OwnerServiceMap();
    }

//    Init Bean Vet
    @Bean
    public VetService InitVetService(){
        return new VetServiceMap();
    }

// Init Bean PetType
    @Bean
    public PetTypeService InitPetTypeService(){
        return new PetTypeServiceMap();
    }
}
