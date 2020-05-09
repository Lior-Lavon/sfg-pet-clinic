package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VisitControllerTest {

    public static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "/pets/createOrUpdateVisitForm";

    @InjectMocks
    VisitController visitController;

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        // create mockito mock
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        Pet pet = Pet.builder().id(2L).birthDate(LocalDate.of(2020, 5, 1)).name("Cat").build();
        when(petService.findById(anyLong())).thenReturn(pet);

    }

    @Test
    void testInitVisitForm() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/2/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void testProcessVisitForm() throws Exception {

        Visit newVisit = Visit.builder().date(LocalDate.now()).description("description").build();
        when(visitService.save(newVisit)).thenReturn(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/2/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)    // mimic a form post
                .param("date", "2020-05-01")                        // mimic an empty string
                .param("description", "some string")
        )   // some description
                .andExpect(status().is3xxRedirection()) // expect 3-2 status of redirection
                .andExpect(view().name("redirect:/owners/1")) // redirection string
                .andExpect(model().attributeExists("pet"));

    }

    @Test
    void testInitEditVisitForm() {
    }

}