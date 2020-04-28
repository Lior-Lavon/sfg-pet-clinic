package guru.springframework.petdataweb.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

//@RequestMapping("/vets")
@Controller
public class VetController {

    private static VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

//    @RequestMapping({"", "/", "/index", "/index.html", ".html"})
    @RequestMapping({"/vets", "/vets/", "/vets.html", "/index", "/index.html", ".html"})
    public String showVets(Model model){

        Set<Vet> vets = vetService.findAll();

        List<Vet> list = new ArrayList<>(vets);
        Collections.sort(list, new Comparator<Vet>() {
            @Override
            public int compare(Vet o1, Vet o2) {
                Long v = o1.getId() - o2.getId();
                return v.intValue();
            }
        });

        model.addAttribute("vets", list);
        return "vets/index";
    }
}
