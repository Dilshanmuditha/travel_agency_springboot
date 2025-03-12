package com.amali.travel.repository;

import com.amali.travel.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByEmail(String email);

    Optional<Vehicle> findById(Integer id);
}
