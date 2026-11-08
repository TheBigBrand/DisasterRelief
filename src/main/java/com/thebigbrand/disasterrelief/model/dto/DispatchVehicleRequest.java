package com.thebigbrand.disasterrelief.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispatchVehicleRequest {
    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;
}