package com.ecommerce.shipping.controller;

import com.ecommerce.shipping.model.Shipping;
import com.ecommerce.shipping.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping("/{orderId}")
    public Shipping getShippingByOrderId(@PathVariable Long orderId){
        return shippingService.getShippingByOrderId(orderId);
    }
    @PostMapping("/create")
    public Shipping createShipment(@RequestParam Long orderId){
        return shippingService.createShipment(orderId);
    }
    @PutMapping("/update-status")
    public void updateShippingStatus(@RequestParam Long orderId, @RequestParam String status) {
        shippingService.updateShippingStatus(orderId, status);
    }
}
