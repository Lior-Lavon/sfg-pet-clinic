package guru.springframework.petdataweb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// junit-5.0
// this is an Integration test that loads the Context
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PetClinicApplicationTest {

    @Test
    public void contextLoads(){

    }
}