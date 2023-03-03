package ibf.ssf.batch2.exam.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Min;

public class ShoppingCart implements Serializable{
    private String item;

    @Min(value=1, message="You must add at least 1 item.")
    private int quantity;

    public ShoppingCart(){}

    public ShoppingCart(String item, int quantity){
        this.item = item; 
        this.quantity = quantity; 
    }

    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 

    @Override
    public String toString(){
        return "Order{item=%s, quantity=%d}".formatted(item, quantity);
    }

    public static ShoppingCart create(JsonObject json) {
        ShoppingCart shoppingCart = new ShoppingCart(); 
        shoppingCart.setItem(json.getString("item"));
        shoppingCart.setQuantity(json.getInt("quantity"));
        return shoppingCart;
    }

}
