package com.thebigbrand.disasterrelief.model.dto;

import jakarta.validation.constraints.NotNull;

public record DispatchVehicleRequest (
    @NotNull(message = "Vehicle ID is required")
    Long vehicleId
) {}