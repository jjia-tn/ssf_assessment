package ibf2022.batch2.ssf.ssf_assessment.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import ibf2022.batch2.ssf.ssf_assessment.model.Cart;
import ibf2022.batch2.ssf.ssf_assessment.model.Item;
import ibf2022.batch2.ssf.ssf_assessment.model.Quotation;

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

    

    public float calculateCost(Cart cart) throws Exception {

        float total = 0f;

        List<String> listOfItems = new LinkedList<>();

        List<Item> temp = cart.getContents();

        for (int i = 0; i < temp.size(); i++) {

            String toAdd = temp.get(i).getItem();
            listOfItems.add(toAdd);
        }

        Quotation quotation = QuotationService.getQuotations(listOfItems);
        Map<String, Float> quotes = quotation.getQuotations();

        for (int i = 0; i < temp.size(); i++) {

            String obj = temp.get(i).getItem();
            int quantity = temp.get(i).getQuantity();

            if (quotes.containsKey(obj)) {
                
                total += quantity * quotes.get(obj);
            }
        }

        return total;
    }

    public String getOrderId(Cart cart) throws NumberFormatException, Exception {

        String orderId;

        List<String> listOfItems = new LinkedList<>();

        List<Item> temp = cart.getContents();

        for (int i = 0; i < temp.size(); i++) {

            String toAdd = temp.get(i).getItem();
            listOfItems.add(toAdd);
        }

        Quotation quotation = QuotationService.getQuotations(listOfItems);

        orderId = quotation.getQuoteId();

        return orderId;
    }
}
