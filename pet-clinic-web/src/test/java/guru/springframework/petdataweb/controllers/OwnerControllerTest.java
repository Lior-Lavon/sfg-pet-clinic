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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attributeExists("owners"));
    }

    @Test
    void findOwners() throws Exception {

        // build Mock Mvc from indexController
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owners"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(
                Arrays.asList(Owner.builder().id(1L).firstName("lior").lastName("lavon").build(),
                        Owner.builder().id(2L).firstName("lior2").lastName("lavon2").build()));

        // build Mock Mvc from indexController
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is3xxRedirection()) // expect 3xx status of redirection
                .andExpect(view().name("redirect:/owners/ownersList")) // redirection string
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {

        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).firstName("lior").lastName("lavon").build()));

        // build Mock Mvc from indexController
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is3xxRedirection()) // expect 3xx status of redirection
                .andExpect(view().name("redirect:/owners/1")) // redirection string
                .andExpect(model().attributeExists("selections"));
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