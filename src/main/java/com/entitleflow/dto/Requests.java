package com.entitleflow.dto;

import com.entitleflow.entity.Asset;
import com.entitleflow.entity.CustomerOrder;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

public final class Requests {
    private Requests() {}
    public record CustomerRequest(@NotBlank @Size(max=120) String name,@NotBlank @Email @Size(max=180) String email,@NotBlank @Size(max=160) String company) {}
    public record AssetRequest(@NotBlank @Size(max=100) String serialNumber,@NotBlank @Size(max=120) String productModel, Asset.Status status) {}
    public record OrderRequest(@NotNull UUID customerId,@NotBlank @Size(max=120) String productModel,@Min(1) @Max(10000) int quantity) {}
    public record OrderStatusRequest(@NotNull CustomerOrder.Status status) {}
    public record EntitlementRequest(@NotNull UUID customerId,@NotBlank @Size(max=120) String productModel,@NotNull LocalDate activationDate,@NotNull LocalDate expiryDate) {}
}
