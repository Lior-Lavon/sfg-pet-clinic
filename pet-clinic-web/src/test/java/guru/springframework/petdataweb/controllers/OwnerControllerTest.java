package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OwnerControllerTest {

    @InjectMocks
    OwnerController ownerController;

    @Mock
    OwnerService ownerService;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).firstName("lior").build());
        owners.add(Owner.builder().id(2L).firstName("meitar").build());

        // create mockito mock
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void listOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/ownersList"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attributeExists("selections"));
    }

    @Test
    void findOwners() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(
                Arrays.asList(Owner.builder().id(1L).firstName("lior").lastName("lavon").build(),
                        Owner.builder().id(2L).firstName("lior2").lastName("lavon2").build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is3xxRedirection()) // expect 3xx status of redirection
                .andExpect(view().name("redirect:/owners/ownersList")) // redirection string
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {

        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).firstName("lior").lastName("lavon").build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is3xxRedirection()) // expect 3xx status of redirection
                .andExpect(view().name("redirect:/owners/1")) // redirection string
                .andExpect(model().attributeExists("selections"));
    }

    @Test
    void displayOwner() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).firstName("lior").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }

    @Test
    void initCreateForm() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {

        Owner newOwner = Owner.builder().id(1L).firstName("firstName").lastName("lastName").address("address").city("city").build();

        when(ownerService.save(any())).thenReturn(newOwner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)    // mimic a form post
                .param("id", "")                        // mimic an empty string
                .param("description", "some string")
        )   // some description
                .andExpect(status().is3xxRedirection()) // expect 3-2 status of redirection
                .andExpect(view().name("redirect:/owners/1")) // redirection string
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).save(any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void processUpdatingForm() throws Exception {

        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).firstName("lior").build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)    // mimic a form post
                .param("id", "")                        // mimic an empty string
                .param("description", "some string")
        )   // some description
                .andExpect(status().is3xxRedirection()) // expect 3-2 status of redirection
                .andExpect(view().name("redirect:/owners/1")) // redirection string
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).save(any());

    }
}