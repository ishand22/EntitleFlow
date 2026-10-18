package com.entitleflow.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity @Table(name="entitlements")
public class Entitlement {
    public enum Status { ACTIVE, REVOKED, EXPIRED }
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
    @Column(name="product_model",nullable=false,length=120) private String productModel;
    @Column(name="activation_date",nullable=false) private LocalDate activationDate;
    @Column(name="expiry_date",nullable=false) private LocalDate expiryDate;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status;
    protected Entitlement() {}
    public Entitlement(Customer c,String product,LocalDate start,LocalDate end){customer=c;productModel=product;activationDate=start;expiryDate=end;status=Status.ACTIVE;}
    public UUID getId(){return id;} public Customer getCustomer(){return customer;} public String getProductModel(){return productModel;}
    public LocalDate getActivationDate(){return activationDate;} public LocalDate getExpiryDate(){return expiryDate;} public Status getStatus(){return status;} public void setStatus(Status v){status=v;}
}
