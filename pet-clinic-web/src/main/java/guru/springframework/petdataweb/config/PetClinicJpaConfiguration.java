package guru.springframework.petdataweb.config;

import guru.springframework.petclinicdata.SDJpaImp.repositories.OwnerRepository;
import guru.springframework.petclinicdata.SDJpaImp.springDataJPAImpl.OwnerJpaService;
import guru.springframework.petclinicdata.mapImp.*;
import guru.springframework.petclinicdata.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@ComponentScan("guru.springframework")
@Profile("springdatajpa")
public class PetClinicJpaConfiguration {

    //    Init Bean Owner
//    @Bean
//    public OwnerService InitOwnerService(){
//        return new OwnerJpaService();
//    }

}
