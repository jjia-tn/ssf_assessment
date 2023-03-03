package ibf2022.batch2.ssf.ssf_assessment.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import ibf2022.batch2.ssf.ssf_assessment.model.Item;

@Service
public class CartService {
    
    public static final String[] ITEM_NAMES = {
        "apple", "orange", "bread", "cheese", "chicken", "mineral_water", "instant_noodles"
    };

    private final Set<String> itemNames;

    public CartService() {
        itemNames = new HashSet<>(Arrays.asList(ITEM_NAMES));
    }

    public List<ObjectError> validateItem(Item item) {
        List<ObjectError> errors = new LinkedList<>();
        FieldError error;

        if (!itemNames.contains(item.getItem().toLowerCase())) {
            error = new FieldError("item", "item",
                "We do not stock %s".formatted(item.getItem()));
            errors.add(error);
        }

        return errors;
    }
}
