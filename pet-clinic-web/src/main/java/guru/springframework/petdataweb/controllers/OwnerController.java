package guru.springframework.petdataweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {
/*
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model){

        Set<Owner> owners = ownerService.findAll();
        List<Owner> list = new ArrayList(owners);
        Collections.sort(list, new Comparator<Owner>() {
            @Override
            public int compare(Owner o1, Owner o2) {
                Long v = o1.getId()-o2.getId();
                return v.intValue();
            }
        });

        model.addAttribute("owners", list);
        return "owners/index";
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model){

        return "notimplemented";
    }
 */
}
