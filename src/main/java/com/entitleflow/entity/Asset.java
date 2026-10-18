package com.entitleflow.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity @Table(name="assets", uniqueConstraints=@UniqueConstraint(columnNames="serial_number"))
public class Asset {
    public enum Status { AVAILABLE, RESERVED, ALLOCATED }
    @Id @GeneratedValue(strategy=GenerationType.UUID) private UUID id;
    @Column(name="serial_number", nullable=false, length=100) private String serialNumber;
    @Column(name="product_model", nullable=false, length=120) private String productModel;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private Status status;
    protected Asset() {}
    public Asset(String serialNumber,String productModel,Status status){this.serialNumber=serialNumber;this.productModel=productModel;this.status=status;}
    public UUID getId(){return id;} public String getSerialNumber(){return serialNumber;}
    public String getProductModel(){return productModel;} public Status getStatus(){return status;} public void setStatus(Status v){status=v;}
}
