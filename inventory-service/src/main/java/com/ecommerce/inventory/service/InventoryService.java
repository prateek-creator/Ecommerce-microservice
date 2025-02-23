package com.ecommerce.inventory.service;

import com.ecommerce.inventory.model.Inventory;
import com.ecommerce.inventory.repostory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    public int getAvailableStock(Long productId){
        Inventory inventory= inventoryRepository.findByProductId(productId);
        return (inventory!=null)? inventory.getQuantity() :0;
    }

    public boolean updateStock(Long productId, int quantity){
        Inventory inventory=inventoryRepository.findByProductId(productId);
        if(inventory!=null && inventory.getQuantity()>=quantity){
            inventory.setQuantity(inventory.getQuantity()-quantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }
    public void addStock(Long productId, int quantity){
        Inventory inventory=inventoryRepository.findByProductId(productId);
        if(inventory==null){
            inventory = new Inventory(null, productId, quantity);
        }
        else {
            inventory.setQuantity(inventory.getQuantity() + quantity);
        }
        inventoryRepository.save(inventory);
    }
}