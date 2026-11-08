package com.thebigbrand.disasterrelief.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.thebigbrand.disasterrelief.model.Vehicle;
import com.thebigbrand.disasterrelief.service.CrisisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final CrisisService crisisService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(crisisService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(crisisService.getVehicleById(id));
    }

    @PostMapping
    public ResponseEntity<Vehicle> createSupply(@Valid @RequestBody Vehicle vehicle) {
        Vehicle created = crisisService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @Valid @RequestBody Vehicle vehicleDetails) {
        return ResponseEntity.ok(crisisService.updateVehicle(id, vehicleDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        crisisService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}