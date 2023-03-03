package ibf.ssf.batch2.exam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ibf.ssf.batch2.exam.model.Order;
import ibf.ssf.batch2.exam.model.Quotation;
import ibf.ssf.batch2.exam.model.ShoppingCart;
import ibf.ssf.batch2.exam.service.ShoppingService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/view3", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseRestController {
    @Autowired
    private ShoppingService shoppingService; 

    public Quotation getQuotation(List<String> items) throws Exception{
        return null; 
    }
    //make a HTTP call to QSys 
    //with items in JSON array as payload to the Qsys REST Endpoint 
    @PostMapping(path="/quotation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getQuoteId(@PathVariable String quotateId){
        Optional<Order> opt = shoppingService.getQuoteId(quotateId);

        if (opt.isEmpty()){
            JsonObject resp = Json.createObjectBuilder()
                .add("error", "error message")
                .build();
            
        }
        return ResponseEntity.ok(opt.get().toJSON().toString());
    }
   

}
