package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequestMapping("/owners/{ownerId}")
@Controller
public class VisitController {

    public static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "/pets/createOrUpdateVisitForm";

    //private final OwnerService ownerService;
    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void initVisitBinder(WebDataBinder binder){
        binder.setDisallowedFields("id");

        // Bind the text date string to a
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){

            @Override
            public void setAsText(String text){
                LocalDate localDate = LocalDate.parse(text);
                setValue(localDate);
            }
        });
    }

    // Called before each and every @RequestMapping annotated method
    // 2 goals:
    // - Make sure we always have fresh data
    // - since we do not use the session scope, make sure that Pet object always has an Id
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model){
        Pet pet = petService.findById(petId);
        Visit visit = new Visit();
        pet.addVisit(visit);

        model.addAttribute("pet", pet);

        return visit;
    }

    // Spring MVC called method loadPetWithVisit before initVisitForm is called
    @GetMapping("/pets/{petId}/visits/new")
    public String initVisitForm(@PathVariable Long petId, Model model){

        return PETS_CREATE_OR_UPDATE_VISIT_FORM;
    }

    // Spring MVC called method loadPetWithVisit before processVisitForm is called
    @PostMapping("/pets/{petId}/visits/new")
    public String processVisitForm(@PathVariable Long ownerId, @Valid Visit visit, BindingResult result){

        if(result.hasErrors()){
            return PETS_CREATE_OR_UPDATE_VISIT_FORM;
        }

        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }

    // Spring MVC called method loadPetWithVisit before initVisitForm is called
//    @GetMapping("/pets/{petId}/visits/edit")
//    public String initEditVisitForm(@PathVariable Long visitId, Model model){
//
//        return PETS_CREATE_OR_UPDATE_VISIT_FORM;
//    }

}
