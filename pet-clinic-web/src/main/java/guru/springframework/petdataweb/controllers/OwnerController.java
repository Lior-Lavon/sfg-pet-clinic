package guru.springframework.petdataweb.controllers;

import guru.springframework.petclinicdata.modules.Owner;
import guru.springframework.petclinicdata.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model){

        Set<Owner> list = ownerService.findAll();
        model.addAttribute("owners", list);
        return "owners/index";
    }
}
