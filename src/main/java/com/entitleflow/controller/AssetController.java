package com.entitleflow.controller;
import com.entitleflow.dto.Requests.AssetRequest;import com.entitleflow.entity.Asset;import com.entitleflow.service.AssetService;
import jakarta.validation.Valid;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/assets") public class AssetController {
    private final AssetService service;public AssetController(AssetService service){this.service=service;}
    @GetMapping public List<Asset> all(){return service.all();}@GetMapping("/{id}") public Asset get(@PathVariable UUID id){return service.get(id);}
    @PostMapping public ResponseEntity<Asset> create(@Valid @RequestBody AssetRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
}
