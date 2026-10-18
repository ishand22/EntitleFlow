package com.entitleflow.controller;
import com.entitleflow.dto.Requests.EntitlementRequest;import com.entitleflow.entity.Entitlement;import com.entitleflow.service.EntitlementService;
import jakarta.validation.Valid;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/entitlements") public class EntitlementController {
    private final EntitlementService service;public EntitlementController(EntitlementService service){this.service=service;}
    @GetMapping public List<Entitlement> all(){return service.all();}@GetMapping("/{id}") public Entitlement get(@PathVariable UUID id){return service.get(id);}
    @PostMapping public ResponseEntity<Entitlement> create(@Valid @RequestBody EntitlementRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
    @PostMapping("/{id}/activate") public Entitlement activate(@PathVariable UUID id){return service.activate(id);}
    @PostMapping("/{id}/revoke") public Entitlement revoke(@PathVariable UUID id){return service.revoke(id);}
}
