package com.entitleflow.service;
import com.entitleflow.dto.Requests.CustomerRequest;
import com.entitleflow.entity.Customer;
import com.entitleflow.exception.*;
import com.entitleflow.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class CustomerService {
    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo){this.repo=repo;}
    public List<Customer> all(){return repo.findAll();}
    public Customer get(UUID id){return repo.findById(id).orElseThrow(()->new NotFoundException("Customer not found: "+id));}
    public Customer create(CustomerRequest r){if(repo.existsByEmail(r.email()))throw new ConflictException("Customer email is already registered");return repo.save(new Customer(r.name(),r.email(),r.company()));}
    public Customer update(UUID id,CustomerRequest r){Customer c=get(id);if(!c.getEmail().equals(r.email())&&repo.existsByEmail(r.email()))throw new ConflictException("Customer email is already registered");c.setName(r.name());c.setEmail(r.email());c.setCompany(r.company());return repo.save(c);}
    public void delete(UUID id){repo.delete(get(id));}
}
