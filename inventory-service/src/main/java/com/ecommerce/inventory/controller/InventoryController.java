package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public int checkStock(@PathVariable Long productId) {
        return inventoryService.getAvailableStock(productId);
    }

    @PostMapping("/deduct")
    public boolean deductStock(@RequestParam Long productId, @RequestParam int quantity) {
        return inventoryService.updateStock(productId, quantity);
    }

    @PostMapping("/add")
    public void addStock(@RequestParam Long productId, @RequestParam int quantity) {
        inventoryService.addStock(productId, quantity);
    }

}
