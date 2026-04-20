package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // ✅ FIX METHOD (IMPORTANT)
    List<Vehicle> findByGarage_Id(Long garageId);
}