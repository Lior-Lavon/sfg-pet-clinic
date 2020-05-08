package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTest {

    public static final String PETS_CREATE_OR_UPDATE_PETS_FORM = "pets/createOrUpdatePetForm";

    @InjectMocks
    PetController petController;

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    Owner owner;
    Set<PetType> petTypes;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        // create mockito mock
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

    }

    // return a form to create new Pet
    @Test
    void initCreateForm() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_PETS_FORM))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void processCreationForm() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)    // mimic a form post
                .param("id", "")                        // mimic an empty string
                .param("description", "some string")
        )   // some description
                .andExpect(status().is3xxRedirection()) // expect 3-2 status of redirection
                .andExpect(view().name("redirect:/owners/1")); // redirection string
    }

    @Test
    void initUpdateForm() throws Exception{

        owner.getPets().add(Pet.builder().id(2L).name("petname").build());

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(ownerService.save(any())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_PETS_FORM))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));

    }

    @Test
    void processUpdateForm() throws Exception {

        // given
        Pet pet = Pet.builder().id(2L).name("petname").build();
        owner.setPet(pet);


        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petService.save(any())).thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/2/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)    // mimic a form post
                .param("id", "")                        // mimic an empty string
                .param("description", "some string")
        )   // some description
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1")); // redirection string
    }

}