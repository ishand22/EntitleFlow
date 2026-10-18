package com.entitleflow.repository;
import com.entitleflow.entity.Entitlement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface EntitlementRepository extends JpaRepository<Entitlement, UUID> {}
