package com.entitleflow.controller;
import com.entitleflow.dto.Requests.CustomerRequest;import com.entitleflow.entity.Customer;import com.entitleflow.service.CustomerService;
import jakarta.validation.Valid;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/customers") public class CustomerController {
    private final CustomerService service;public CustomerController(CustomerService service){this.service=service;}
    @GetMapping public List<Customer> all(){return service.all();}@GetMapping("/{id}") public Customer get(@PathVariable UUID id){return service.get(id);}
    @PostMapping public ResponseEntity<Customer> create(@Valid @RequestBody CustomerRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
    @PutMapping("/{id}") public Customer update(@PathVariable UUID id,@Valid @RequestBody CustomerRequest r){return service.update(id,r);}
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable UUID id){service.delete(id);return ResponseEntity.noContent().build();}
}
