package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByGarage_Id(Long garageId);

    Optional<Supplier> findBySupplierIdAndGarage_Id(int supplierId, Long garageId);
}