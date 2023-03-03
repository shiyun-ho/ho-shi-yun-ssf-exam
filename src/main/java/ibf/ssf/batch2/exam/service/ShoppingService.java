package ibf.ssf.batch2.exam.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import ibf.ssf.batch2.exam.model.Order;
import ibf.ssf.batch2.exam.model.ShippingAddress;
import ibf.ssf.batch2.exam.model.ShoppingCart;
import ibf.ssf.batch2.exam.repository.ShoppingRepository;

@Service
public class ShoppingService {
    //LATER when repo is written
    @Autowired
    private ShoppingRepository shoppingRepo; 

    //method to include all items
    public static final String[] ITEM_NAMES = {"Apple", "Orange", "Cheese", "Chicken", "Mineral", "Instant Noodles"};

    private final Set<String> itemNames; 

    public ShoppingService(){
        itemNames = new HashSet<>(Arrays.asList(ITEM_NAMES));
    }

    //validate view1.html
    public List<ObjectError> validateShoppingcart(ShoppingCart shoppingCart){
        List<ObjectError> errors = new LinkedList<>(); 
        FieldError error; 

        if (!itemNames.contains(shoppingCart.getItem())){
            error = new FieldError("item", "item", 
            "We do not stock %s".formatted(shoppingCart.getItem()));
            errors.add(error);
        }

        return errors;
        
    }

    //validate view2.html
    public List<ObjectError> validateShippingAddress(ShippingAddress shippingAddress){
        List<ObjectError> errors = new LinkedList<>(); 
        FieldError error; 

        if(shippingAddress.getName().isEmpty() && shippingAddress.getAddress().isEmpty()){
            error = new FieldError("name", "name", "Name and/or address is empty.");
            errors.add(error);
        }

        return errors;
        
    }

    public Optional<Object> getQuoteId(String quoteId){
        return shoppingRepo.get(quoteId);
    }

    public float calculateCost(Order order){
        float total = 1.00f; 

        if (order.getItems().contains("apple")){
            Integer qty = order.getQuantity();
            total = total*qty; 
            total = (float) (total* (0.3));
        }

        if (order.getItems().contains("bread")){
            Integer qty = order.getQuantity();
            total = total*qty; 
            total = (float) (total* (2.5));
        }
    }

    
    
}
