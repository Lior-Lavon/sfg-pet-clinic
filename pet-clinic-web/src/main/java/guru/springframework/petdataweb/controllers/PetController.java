package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

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


}
