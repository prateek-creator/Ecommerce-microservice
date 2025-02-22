package com.ecommerce.payment.controller;

import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.payment.PaymentService;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayment(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Payment> getPaymentByUserId(@PathVariable Long id){
        return paymentService.getPaymentsByUserId(id);
    }

    @GetMapping("order/{orderId}")
    public List<Payment> getPaymentByOrderId(@PathVariable Long id){
        return paymentService.getPaymentsByOrderId(id);
    }

    @PostMapping
    public String processPayment(@RequestParam Long orderId, @RequestParam Long userId, @RequestParam double amount, @RequestParam String paymentMethod){
        return paymentService.processPayment(orderId, userId, amount, paymentMethod);
    }
}
