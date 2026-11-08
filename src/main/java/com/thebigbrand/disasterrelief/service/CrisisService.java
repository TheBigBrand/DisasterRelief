package com.thebigbrand.disasterrelief.service;

import com.thebigbrand.disasterrelief.model.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.thebigbrand.disasterrelief.model.dto.CreateIncidentRequest;
import com.thebigbrand.disasterrelief.model.dto.DispatchVehicleRequest;
import com.thebigbrand.disasterrelief.repository.IncidentRepository;
import com.thebigbrand.disasterrelief.repository.SupplyRepository;
import com.thebigbrand.disasterrelief.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrisisService {

    private final IncidentRepository incidentRepository;
    private final SupplyRepository supplyRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional
    public Incident createIncident(CreateIncidentRequest request) {
        Incident incident = Incident.builder()
                .location(request.getLocation())
                .description(request.getDescription())
                .requiredSupplyType(request.getRequiredSupplyType())
                .requiredSuppliesQuantity(request.getRequiredSuppliesQuantity())
                .status(IncidentStatus.REPORTED)
                .build();
        return incidentRepository.save(incident);
    }

    public List<Supply> getAvailableSupplies() {
        return supplyRepository.findByQuantityGreaterThan(0);
    }

    @Transactional
    public Vehicle dispatchVehicle(Long incidentId, DispatchVehicleRequest request) {
        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new EntityNotFoundException("Incident not found with id: " + incidentId));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + request.getVehicleId()));

        if (vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new IllegalStateException("Vehicle is not available for dispatch");
        }

        vehicle.setAssignedIncident(incident);
        vehicle.setStatus(VehicleStatus.DISPATCHED);
        incident.setStatus(IncidentStatus.DISPATCHED);

        incidentRepository.save(incident);
        return vehicleRepository.save(vehicle);
    }

    // Incident CRUD
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Incident not found with id: " + id));
    }

    @Transactional
    public Incident updateIncident(Long id, CreateIncidentRequest request) {
        Incident incident = getIncidentById(id);
        incident.setLocation(request.getLocation());
        incident.setDescription(request.getDescription());
        incident.setRequiredSupplyType(request.getRequiredSupplyType());
        incident.setRequiredSuppliesQuantity(request.getRequiredSuppliesQuantity());
        return incidentRepository.save(incident);
    }

    @Transactional
    public void deleteIncident(Long id) {
        Incident incident = getIncidentById(id);
        incidentRepository.delete(incident);
    }

    // Supply CRUD
    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    public Supply getSupplyById(Long id) {
        return supplyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supply not found with id: " + id));
    }

    @Transactional
    public Supply addOrUpdateSupply(Supply supply) {
        return supplyRepository.save(supply);
    }

    @Transactional
    public Supply updateSupply(Long id, Supply supplyDetails) {
        Supply supply = getSupplyById(id);
        supply.setName(supplyDetails.getName());
        supply.setQuantity(supplyDetails.getQuantity());
        supply.setUnit(supplyDetails.getUnit());
        return supplyRepository.save(supply);
    }

    @Transactional
    public void deleteSupply(Long id) {
        Supply supply = getSupplyById(id);
        supplyRepository.delete(supply);
    }

    // Vehicle CRUD
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + id));
    }

    @Transactional
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setCallSign(vehicleDetails.getCallSign());
        vehicle.setStatus(vehicleDetails.getStatus());
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }
}