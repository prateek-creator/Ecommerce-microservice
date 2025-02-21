package com.ecommerce.order.service;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrderByUserId(Long id){
        return orderRepository.findByUserId(id);
    }

    public Order placeOrder(Long userId, Long productId, int quantity, double totalPrice){
        Order order=new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }
    public void cancelOrder(Long id){
        orderRepository.deleteById(id);
    }
}
