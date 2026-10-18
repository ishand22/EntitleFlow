package com.entitleflow.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name="customer_orders")
public class CustomerOrder {
    public enum Status { CREATED, PROCESSING, COMPLETED, FAILED }
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    @ManyToOne(optional=false, fetch=FetchType.EAGER) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
    @Column(name="product_model",nullable=false,length=120) private String productModel;
    @Column(nullable=false) private int quantity;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status;
    @Column(nullable=false,updatable=false) private Instant createdAt;
    protected CustomerOrder() {}
    public CustomerOrder(Customer customer,String productModel,int quantity){this.customer=customer;this.productModel=productModel;this.quantity=quantity;this.status=Status.CREATED;this.createdAt=Instant.now();}
    public UUID getId(){return id;} public Customer getCustomer(){return customer;} public String getProductModel(){return productModel;}
    public int getQuantity(){return quantity;} public Status getStatus(){return status;} public void setStatus(Status v){status=v;} public Instant getCreatedAt(){return createdAt;}
}
