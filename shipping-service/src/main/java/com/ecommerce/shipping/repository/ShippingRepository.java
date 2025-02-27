package com.ecommerce.shipping.repository;

import com.ecommerce.shipping.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Long> {
    Shipping findByOrderId(Long id);
}
