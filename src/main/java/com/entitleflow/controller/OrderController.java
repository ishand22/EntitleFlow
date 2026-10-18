package com.entitleflow.controller;
import com.entitleflow.dto.Requests.*;import com.entitleflow.entity.CustomerOrder;import com.entitleflow.service.OrderService;
import jakarta.validation.Valid;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/orders") public class OrderController {
    private final OrderService service;public OrderController(OrderService service){this.service=service;}
    @GetMapping public List<CustomerOrder> all(){return service.all();}@GetMapping("/{id}") public CustomerOrder get(@PathVariable UUID id){return service.get(id);}
    @PostMapping public ResponseEntity<CustomerOrder> create(@Valid @RequestBody OrderRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
    @PatchMapping("/{id}/status") public CustomerOrder status(@PathVariable UUID id,@Valid @RequestBody OrderStatusRequest r){return service.status(id,r.status());}
}
