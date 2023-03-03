package ibf.ssf.batch2.exam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf.ssf.batch2.exam.model.Order;
import ibf.ssf.batch2.exam.model.ShoppingCart;

@Repository
public class ShoppingRepository {
    @Autowired @Qualifier("shoppingcart")
    private RedisTemplate<String, String> redisTemplate; 

    public void save(Order order) {
        redisTemplate.opsForValue().set(
            order.getOrderId(), order.toJSON().toString());
    } 

    //set the value of redis key to json string representation of the order
    public Optional<Object> get(String orderId) {
        // Get the order JSON string from Redis based on the provided order ID
    String json = redisTemplate.opsForValue().get(orderId);

        // If the JSON string is null or empty, return an empty Optional
    if ((null == json) || (json.trim().length() <= 0))
        return Optional.empty();

        // Create an Order object from the retrieved JSON string and return it in an Optional	
    return Optional.of(Order.create(json));
}

    public List<ShoppingCart> findAll() {
        return null;
    }
}



