package com.ecommerce.payment.payment;

import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id){
        return paymentRepository.findById(id).orElse(null);
    }

    public List<Payment> getPaymentsByUserId(Long userId){
            return paymentRepository.findByUserId(userId);
    }

    public List<Payment> getPaymentsByOrderId(Long orderId){
        return paymentRepository.findByOrderId(orderId);
    }

    public String processPayment(Long orderId, Long userId, double amount, String paymentMethod){
        Payment payment=new Payment();
        payment.setOrderId(orderId);
        payment.setUserId(userId);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus("COMPLETED");
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return "SUCCESS";
    }
}
