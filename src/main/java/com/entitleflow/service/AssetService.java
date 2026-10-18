package com.entitleflow.service;
import com.entitleflow.dto.Requests.AssetRequest;
import com.entitleflow.entity.Asset;
import com.entitleflow.exception.*;
import com.entitleflow.repository.AssetRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class AssetService {
    private final AssetRepository repo;
    public AssetService(AssetRepository repo){this.repo=repo;}
    public List<Asset> all(){return repo.findAll();}
    public Asset get(UUID id){return repo.findById(id).orElseThrow(()->new NotFoundException("Asset not found: "+id));}
    public Asset create(AssetRequest r){if(repo.existsBySerialNumber(r.serialNumber()))throw new ConflictException("Asset serial number is already registered");return repo.save(new Asset(r.serialNumber(),r.productModel(),r.status()==null?Asset.Status.AVAILABLE:r.status()));}
}
