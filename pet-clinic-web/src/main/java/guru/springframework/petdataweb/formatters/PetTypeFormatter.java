package guru.springframework.petdataweb.formatters;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

// Spring Component that implement a Formatter interface for PetType
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String textValue, Locale locale) throws ParseException {
        Optional<PetType> petTypeOptional = petTypeService.findAll().stream()
                .filter(petType -> petType.getName().equals(textValue))
                .findFirst();
        if(!petTypeOptional.isPresent())
            throw  new RuntimeException("PetType not found : " + textValue);

        return petTypeOptional.get();
    }

}
