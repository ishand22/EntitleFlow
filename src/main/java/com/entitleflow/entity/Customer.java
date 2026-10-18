package com.entitleflow.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity @Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(nullable = false, length = 120) private String name;
    @Column(nullable = false, length = 180) private String email;
    @Column(nullable = false, length = 160) private String company;
    protected Customer() {}
    public Customer(String name, String email, String company) { this.name=name; this.email=email; this.company=company; }
    public UUID getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getCompany(){return company;} public void setCompany(String v){company=v;}
}
