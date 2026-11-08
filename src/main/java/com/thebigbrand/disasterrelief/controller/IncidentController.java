package com.thebigbrand.disasterrelief.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.thebigbrand.disasterrelief.model.Incident;
import com.thebigbrand.disasterrelief.model.Vehicle;
import com.thebigbrand.disasterrelief.model.dto.CreateIncidentRequest;
import com.thebigbrand.disasterrelief.model.dto.DispatchVehicleRequest;
import com.thebigbrand.disasterrelief.service.CrisisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final CrisisService crisisService;

    @PostMapping
    public ResponseEntity<Incident> reportIncident(@Valid @RequestBody CreateIncidentRequest request) {
        Incident created = crisisService.createIncident(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/dispatch/{id}")
    public ResponseEntity<Vehicle> dispatchVehicle(
            @PathVariable("id") Long incidentId,
            @Valid @RequestBody DispatchVehicleRequest request) {
        Vehicle vehicle = crisisService.dispatchVehicle(incidentId, request);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        return ResponseEntity.ok(crisisService.getAllIncidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        return ResponseEntity.ok(crisisService.getIncidentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @Valid @RequestBody CreateIncidentRequest request) {
        return ResponseEntity.ok(crisisService.updateIncident(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        crisisService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }
}