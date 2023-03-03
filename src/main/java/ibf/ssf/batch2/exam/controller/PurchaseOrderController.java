package ibf.ssf.batch2.exam.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ibf.ssf.batch2.exam.model.ShippingAddress;
import ibf.ssf.batch2.exam.model.ShoppingCart;
import ibf.ssf.batch2.exam.service.ShoppingService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PurchaseOrderController {
    @Autowired
    private ShoppingService shoppingSvc; 

    private Logger logger = Logger.getLogger(">>>Successfully introduced " + PurchaseOrderController.class.getName());

    @GetMapping(path={"/shoppingcart","/"})
    public String getShoppingCart(Model model, HttpSession session){
        session.invalidate();
        model.addAttribute("shoppingcart", new ShoppingCart());
        return "shoppingcart"; 
    }

    // @PostMapping(path={"/shoppingcart"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(path={"/shoppingcart"})
    public String postShopping(Model model, HttpSession session, @Valid ShoppingCart shoppingCart, BindingResult result){
        //shows log that it is successful in injecting shopping cart
        logger.info("POST /shoppingCart: %s".formatted(shoppingCart.toString()));
        
        if (result.hasErrors()){
            return "shoppingcart";
        }

        //create a list of errors
        List<ObjectError> errors = shoppingSvc.validateShoppingcart(shoppingCart);
        //if list of errors is not empty
        if (!errors.isEmpty()){
            for (ObjectError i : errors){
                result.addError(i);
            
            return "shoppingcart";}
        }

        //else if there are no errors
        session.setAttribute("shoppingCart", shoppingCart);

        //possible to store informaiton in method Order (L89)

        model.addAttribute("shippingAddress", new ShippingAddress());
        
        return "shippingaddress";
    }

    // @GetMapping("/shoppingCart")
    // public String getShoppingCart(Model mode){
    //     List<ShoppingCart> cart = shoppingSvc.getAllCart();
    //     mode.addAttribute("shoppingCart", cart);
    //     return "shoppingCart"; 
    // }

    @GetMapping(path={"/shippingaddress"})
    public String getShippingAddress(Model model, HttpSession session){
        // session.invalidate();
        model.addAttribute("shippingaddress", new ShippingAddress());
        return "shippingaddress"; 
    }

    //when user submits name and address
    @PostMapping(path={"/shippingaddress"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postShipping(Model model, HttpSession session, @Valid ShippingAddress shippingAddress, BindingResult result){
        //shows log that it is successful in injecting shopping cart
        logger.info("POST /shoppingCart: %s".formatted(shippingAddress.toString()));
        
        //if binding result presents error
        if (result.hasErrors()){
            logger.info(">>>User has not keyed in the fields correctly. Please try again.");
            return "shoppingcart";
        }
        
        //else if there are no errors
        //may need to address duplicate shippingAddress2
        ShippingAddress shippingAddress2 = (ShippingAddress) session.getAttribute("shippingAddress");
        model.addAttribute("shippingAddress", new ShippingAddress());
        
        return "view3";
    }


}
