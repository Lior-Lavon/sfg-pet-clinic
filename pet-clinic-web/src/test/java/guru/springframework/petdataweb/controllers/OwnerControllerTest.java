package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OwnerControllerTest {

    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    Set<Owner> owners;

    @BeforeEach
    void setUp() {

        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).firstName("lior").build());
        owners.add(Owner.builder().id(2L).firstName("meitar").build());

        // create mockito mock
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void listOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        // build Mock Mvc from indexController
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {

//        // build Mock Mvc from indexController
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("notimplementedyet"));
//
//
//        verifyZeroInteractions(ownerService);
    }

    @Test
    void displayOwner() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).firstName("lior").build());

        // build Mock Mvc from indexController
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }
}