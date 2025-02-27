package com.ecommerce.order.controller;


import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Order> getAllOrders(){
        return  orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public  Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{id}")
    public List<Order> getOrderByUserId(@PathVariable Long userId){
        return orderService.getOrderByUserId(userId);
    }

    @PostMapping
    public String placeOrder(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity,@RequestParam double totalPrice,@RequestParam String paymentMethod){
        return orderService.placeOrder(userId, productId, quantity, totalPrice, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }
    @GetMapping("/{orderId}/shipping")
    public String getShippingStatus(@PathVariable Long orderId){
        String shippingUrl = "http://localhost:8086/shipping/" + orderId;
        return restTemplate.getForObject(shippingUrl, String.class);
    }

}
