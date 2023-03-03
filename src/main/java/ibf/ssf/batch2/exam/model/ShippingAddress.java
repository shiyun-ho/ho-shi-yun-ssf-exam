package ibf.ssf.batch2.exam.model;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ShippingAddress {

    @NotNull(message="You must input a name")
    @Size(min=2, message="Your name must be at least 2 characters long.")
    private String name; 

    @NotEmpty(message="Compulsory field")
    private String address; 

    public ShippingAddress(){}

    public ShippingAddress(String name, String address){
        this.name=name;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){
        return "shippingAddress{name=%s, address=%s}".formatted(name, address);
    }

    public static ShippingAddress create(JsonObject json) {
        ShippingAddress shippingAddress = new ShippingAddress(); 
        shippingAddress.setName(json.getString("name"));
        shippingAddress.setAddress(json.getString("address"));
        return shippingAddress;
    }
}
