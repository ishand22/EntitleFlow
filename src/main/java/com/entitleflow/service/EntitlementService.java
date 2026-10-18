package com.entitleflow.service;
import com.entitleflow.dto.Requests.EntitlementRequest;
import com.entitleflow.entity.*;
import com.entitleflow.exception.NotFoundException;
import com.entitleflow.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
@Service public class EntitlementService {
    private final EntitlementRepository repo;private final CustomerRepository customers;
    public EntitlementService(EntitlementRepository repo,CustomerRepository customers){this.repo=repo;this.customers=customers;}
    private Entitlement current(Entitlement e){if(e.getStatus()==Entitlement.Status.ACTIVE&&e.getExpiryDate().isBefore(LocalDate.now()))e.setStatus(Entitlement.Status.EXPIRED);return e;}
    public List<Entitlement> all(){return repo.findAll().stream().map(this::current).toList();}
    public Entitlement get(UUID id){Entitlement e=repo.findById(id).orElseThrow(()->new NotFoundException("Entitlement not found: "+id));return current(e);}
    public Entitlement create(EntitlementRequest r){if(r.expiryDate().isBefore(r.activationDate()))throw new IllegalArgumentException("Expiry date must be on or after activation date");Customer c=customers.findById(r.customerId()).orElseThrow(()->new NotFoundException("Customer not found: "+r.customerId()));return repo.save(new Entitlement(c,r.productModel(),r.activationDate(),r.expiryDate()));}
    public Entitlement revoke(UUID id){Entitlement e=get(id);if(e.getStatus()!=Entitlement.Status.ACTIVE)throw new IllegalArgumentException("Only active entitlements can be revoked");e.setStatus(Entitlement.Status.REVOKED);return repo.save(e);}
    public Entitlement activate(UUID id){Entitlement e=get(id);if(e.getStatus()!=Entitlement.Status.REVOKED)throw new IllegalArgumentException("Only revoked entitlements can be activated");if(e.getExpiryDate().isBefore(LocalDate.now()))throw new IllegalArgumentException("Expired entitlements cannot be activated");e.setStatus(Entitlement.Status.ACTIVE);return repo.save(e);}
}
