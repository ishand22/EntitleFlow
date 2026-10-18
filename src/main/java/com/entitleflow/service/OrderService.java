package com.entitleflow.service;
import com.entitleflow.dto.Requests.OrderRequest;
import com.entitleflow.entity.*;
import com.entitleflow.exception.NotFoundException;
import com.entitleflow.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class OrderService {
    private final CustomerOrderRepository orders;private final CustomerRepository customers;
    public OrderService(CustomerOrderRepository orders,CustomerRepository customers){this.orders=orders;this.customers=customers;}
    public List<CustomerOrder> all(){return orders.findAll();}
    public CustomerOrder get(UUID id){return orders.findById(id).orElseThrow(()->new NotFoundException("Order not found: "+id));}
    public CustomerOrder create(OrderRequest r){Customer c=customers.findById(r.customerId()).orElseThrow(()->new NotFoundException("Customer not found: "+r.customerId()));return orders.save(new CustomerOrder(c,r.productModel(),r.quantity()));}
    public CustomerOrder status(UUID id,CustomerOrder.Status status){CustomerOrder o=get(id);o.setStatus(status);return orders.save(o);}
}
