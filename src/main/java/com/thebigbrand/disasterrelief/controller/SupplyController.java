package com.thebigbrand.disasterrelief.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.thebigbrand.disasterrelief.model.Supply;
import com.thebigbrand.disasterrelief.service.CrisisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplies")
@RequiredArgsConstructor
public class SupplyController {

    private final CrisisService crisisService;

    @GetMapping("/available")
    public ResponseEntity<List<Supply>> getAvailableSupplies() {
        List<Supply> supplies = crisisService.getAvailableSupplies();
        return ResponseEntity.ok(supplies);
    }

    @GetMapping
    public ResponseEntity<List<Supply>> getAllSupplies() {
        return ResponseEntity.ok(crisisService.getAllSupplies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supply> getSupplyById(@PathVariable Long id) {
        return ResponseEntity.ok(crisisService.getSupplyById(id));
    }

    @PostMapping
    public ResponseEntity<Supply> createSupply(@Valid @RequestBody Supply supply) {
        Supply created = crisisService.addOrUpdateSupply(supply);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supply> updateSupply(@PathVariable Long id, @Valid @RequestBody Supply supplyDetails) {
        return ResponseEntity.ok(crisisService.updateSupply(id, supplyDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Long id) {
        crisisService.deleteSupply(id);
        return ResponseEntity.noContent().build();
    }
}