package com.ecommerce.order.service;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrderByUserId(Long id){
        return orderRepository.findByUserId(id);
    }

    public String placeOrder(Long userId, Long productId, int quantity, double totalPrice, String paymentMethod){

        String inventoryUrl = "http://localhost:8085/inventory/" + productId;
        Integer availableStock=restTemplate.getForObject(inventoryUrl, Integer.class);
        if(availableStock==null || availableStock<quantity ){
            return "order failed not enough stock available";
        }
        String deductStockUrl="http://localhost:8085/inventory/deduct?productId=" + productId + "&quantity=" + quantity;
        Boolean stockDeducted=restTemplate.postForObject(deductStockUrl,null, Boolean.class);
        if(!stockDeducted){
            return "Order failed: unable to deduct stock";
        }

        Order order=new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder=orderRepository.save(order);

        //calling payment-service
        String paymentUrl = "http://localhost:8084/payments?orderId=" + savedOrder.getId()
                + "&userId=" + userId + "&amount=" + totalPrice + "&paymentMethod=" + paymentMethod;
        String paymentResponse=restTemplate.postForObject(paymentUrl,null, String.class);

        if(("SUCCESS").equalsIgnoreCase(paymentResponse)){
            savedOrder.setStatus("PAID");
            orderRepository.save(savedOrder);
            String shippingUrl="http://localhost:8086/shipping/create?orderId="+savedOrder.getId();
            restTemplate.postForObject(shippingUrl,null, String.class);
        }
        else{
            savedOrder.setStatus("FAILED");
            orderRepository.save(savedOrder);
        }

        return "Order placed successfully with ID: " + savedOrder.getId();
    }
    public void cancelOrder(Long id){
        orderRepository.deleteById(id);
    }
}
