package guru.springframework.sfgpetclinic.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestUserTest {

    @Test
    void getLastName() {

        String name = "lior";
        assertEquals("lior", name);
    }
}