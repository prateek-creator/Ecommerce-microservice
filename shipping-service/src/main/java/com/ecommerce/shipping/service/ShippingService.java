package com.ecommerce.shipping.service;

import com.ecommerce.shipping.model.Shipping;
import com.ecommerce.shipping.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;
    public Shipping getShippingByOrderId(Long id){
        return shippingRepository.findByOrderId(id);
    }

    public Shipping createShipment(Long orderId){
        String trackingNumber="TRACK"+ new Random().nextInt(10000);
        LocalDateTime shippedDate=LocalDateTime.now();
        LocalDateTime expectedDeliveryDate=shippedDate.plusDays(5);
        Shipping shipping = new Shipping(orderId, trackingNumber, "SHIPPED", shippedDate, expectedDeliveryDate);
        return shippingRepository.save(shipping);
    }

    public void  updateShippingStatus(Long orderId, String status){
        Shipping shipping=shippingRepository.findByOrderId(orderId);
        if(shipping!=null){
            shipping.setStatus(status);
            shippingRepository.save(shipping);
        }
    }
}
