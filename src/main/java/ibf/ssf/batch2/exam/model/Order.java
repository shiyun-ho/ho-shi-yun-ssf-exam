package ibf.ssf.batch2.exam.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {

    private float totalCost = 0f; 
    //private String orderId; 

    private final ShoppingCart shoppingCart; 
    private final ShippingAddress shippingAddress; 
    private final Quotation quotation; 

    public Order(ShoppingCart shoppingCart, ShippingAddress shippingAddress){
        this.shoppingCart = shoppingCart; 
        this.shippingAddress = shippingAddress; 
    }

    // public String getOrderId() {
    //     return this.orderId; 
    // }
    // public void setOrderId(String orderId) {
    //         this.orderId = orderId;
    // }


    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public String getQuoteId() {return this.quotation.getQuoteId();}
    public void setQuoteId() {this.setQuoteId();}
    public String getItems() {return this.shoppingCart.getItem();}
    public int getQuantity() {return this.shoppingCart.getQuantity();}

    //to convert to json string
    @Override
    public String toString(){
        return "Order{quoteId=%, quotations=$}".formatted(quotation.getQuoteId(), shoppingCart.getItem());
    }

    public static Order create(){
        JsonObject json = toJSON(); 
        ShoppingCart shoppingCart = ShoppingCart.create(json);
        ShippingAddress shippingAddress = ShippingAddress.create(json);
        Order order = new Order(shoppingCart, shippingAddress); 
        order.setQuoteId();
        order.setTotalCost((float)json.getJsonNumber("totalCost").doubleValue());
        return order;
        
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("quoteId", getQuoteId())
            .add("quotations", getItems())
            .build();
    }

}
