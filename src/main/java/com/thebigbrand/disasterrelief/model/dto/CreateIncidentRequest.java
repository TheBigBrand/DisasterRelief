package com.thebigbrand.disasterrelief.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateIncidentRequest (
    @NotBlank(message = "Location is required")
    String location,

    @NotBlank(message = "Description is required")
    String description,

    String requiredSupplyType,
    Integer requiredSuppliesQuantity
) {}