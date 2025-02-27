package com.ecommerce.shipping.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String trackingNumber;
    private String status;
    private LocalDateTime shippedDate;
    private LocalDateTime expextedDeliveryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public LocalDateTime getExpextedDeliveryDate() {
        return expextedDeliveryDate;
    }

    public void setExpextedDeliveryDate(LocalDateTime expextedDeliveryDate) {
        this.expextedDeliveryDate = expextedDeliveryDate;
    }

    public Shipping() {
    }

    public Shipping(Long orderId, String trackingNumber, String status, LocalDateTime shippedDate, LocalDateTime expextedDeliveryDate) {
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.shippedDate = shippedDate;
        this.expextedDeliveryDate = expextedDeliveryDate;
    }
}
