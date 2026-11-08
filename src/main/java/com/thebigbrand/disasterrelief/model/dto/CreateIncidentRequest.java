package com.thebigbrand.disasterrelief.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateIncidentRequest {
    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Description is required")
    private String description;

    private String requiredSupplyType;
    private Integer requiredSuppliesQuantity;
}