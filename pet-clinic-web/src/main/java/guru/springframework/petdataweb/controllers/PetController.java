package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RequestMapping("/owners/{ownerId}")
@Controller
public class PetController {

    public static final String PETS_CREATE_OR_UPDATE_PETS_FORM = "pets/createOrUpdatePetsForm";

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetTypeService petTypeService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder binder){
        binder.setDisallowedFields("id");
    }

    // helper methods that will be populate to the model
    //---------------------------------------------------
    // populate the petType array to the variable 'type'
    @ModelAttribute("type")
    public Collection<PetType> populatePetType(){
        return petTypeService.findAll();
    }

    // fill up the owner attribute
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }


    @GetMapping("/pets/{petId}/edit")
    public String initEditPetForm(@PathVariable Long ownerId, Model model){
        // find owner
        Owner existOwner = ownerService.findById(ownerId);

        // find the existing pet
        Optional<Pet> optionalExistingPet = existOwner.getPets().stream()
                .filter(pet1 -> pet1.getId().equals(petId))
                .findFirst();

        if(!optionalExistingPet.isPresent()){
            // todo
            throw new RuntimeException("pet not found id:" + petId);
        }

        Pet pet = optionalExistingPet.get();
        model.addAttribute("pet", pet);
        model.addAttribute("types", populatePetType());

        return PETS_CREATE_OR_UPDATE_PETS_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processEditPetForm(@PathVariable Long ownerId, @PathVariable Long petId, @Valid Pet pet, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_PETS_FORM;
        }

        // find owner
        Owner existOwner = ownerService.findById(ownerId);

        // find the existing pet
        Optional<Pet> optionalExistingPet = existOwner.getPets().stream()
                .filter(pet1 -> pet1.getId().equals(petId))
                .findFirst();

        if(!optionalExistingPet.isPresent()){
            // todo
            throw new RuntimeException("pet not found id:" + petId);
        }

        Pet existingPet = optionalExistingPet.get();
        existingPet.setPetType(pet.getPetType());
        existingPet.setBirthDate(pet.getBirthDate());
        existingPet.setName(pet.getName());
        existingPet.setVisits(pet.getVisits());

        ownerService.save(existOwner);

        model.addAttribute("pet", existingPet);

        return "redirect:/owners/" + ownerId;
    }
}
