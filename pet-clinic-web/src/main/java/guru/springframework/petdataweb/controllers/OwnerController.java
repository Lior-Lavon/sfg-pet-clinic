package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "/owners/createOrUpdateOwnerForm";
    public static final String VIEWS_OWNER_CREATE_OR_UPDATED_FORM = "View owner create or updated form";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // using WebDataBinder we can intercept the data that is passed to the controller before any post method is called
    // in this case we define that the field 'id' should not be allowed into the controller
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/ownersList")
    public String listOwners(Model model){

        List<Owner> list = new ArrayList(ownerService.findAll());
        Collections.sort(list, new Comparator<Owner>() {
            @Override
            public int compare(Owner o1, Owner o2) {
                Long v = o1.getId()-o2.getId();
                return v.intValue();
            }
        });

        model.addAttribute("selections", list);
        return "owners/ownersList";
    }

    // return the find owner Form
    @RequestMapping({"/find"})
    public String findOwnersForm(Model model){

        Owner owner = Owner.builder().build();
        model.addAttribute("owner", owner);
        return "owners/findOwners";
    }

    // perform a search for the owners
    @GetMapping({"", "/"})
    public String processOwnerFindForm(Owner owner, BindingResult result, Model model){

        if(owner.getLastName() == null)
            owner.setLastName("");

        List<Owner> ownerList = ownerService.findAllByLastNameLike('%' + owner.getLastName() + '%');
        if(ownerList.isEmpty()){
            // no owners found
            result.rejectValue("lastName", "NotFound", "not found");
            model.addAttribute("owners", Owner.builder());
            return "/owners/findOwners";
        } else if(ownerList.size()==1){
            // one owner found
            model.addAttribute("selections", ownerList.get(0));
            return "redirect:/owners/" + ownerList.get(0).getId();
        } else {
            // more then 1 found
            model.addAttribute("selections", ownerList);
            return "redirect:/owners/ownersList";
        }
    }

    // show one single owner by id

    //  version 1
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId){

        Owner owner = ownerService.findById(ownerId);

        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", owner);

        return mav;
    }

//    version 2
//    @GetMapping("/{ownerId}")
//    public String showOwner(Model model, @PathVariable("ownerId") Long ownerId){
//
//        Owner owner = ownerService.findById(ownerId);
//        model.addAttribute("owner", owner);
//
//        return "/owners/ownerDetails";
//    }

    @GetMapping("/new")
    public String showNewOwnerForm(Model model){
        // send new owner
        model.addAttribute("owner", Owner.builder().build());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String saveOrUpdate(@Valid Owner newOwner, BindingResult result){

        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATED_FORM;
        } else {
            Owner savedOwner = ownerService.save(newOwner);
            return "redirect:/owners/" + savedOwner.getId() ;
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model){

        // send existing owner
        model.addAttribute("owner", ownerService.findById(Long.valueOf(ownerId)));
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, @PathVariable Long ownerId, BindingResult result){

        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATED_FORM;
        } else {
            // becouse dataBinder.setDisallowedFields("id"); we need to set the Id to the Owner
            owner.setId(ownerId);
            Owner updatedOwner = ownerService.save(owner);
            return "redirect:/owners/" + updatedOwner.getId() ;
        }
    }

}
