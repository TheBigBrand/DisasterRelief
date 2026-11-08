package com.thebigbrand.disasterrelief.repository;

import com.thebigbrand.disasterrelief.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
    List<Supply> findByQuantityGreaterThan(Integer minQuantity);
}