package com.garage.project.Owner.Service;


import com.garage.project.Owner.Dto.SupplierDTO;
import java.util.List;

public interface SupplierService {

    // Save a supplier
    void save(SupplierDTO dto);

    // Get all suppliers
    List<SupplierDTO> getAll();

    // Get all suppliers for a specific garage
    List<SupplierDTO> getByGarageId(Long  garageId);

    // Get a single supplier by supplierId and garageId
    SupplierDTO getByGarageAndSupplier(int id, Long  garageId);

    // Get supplier by ID
    SupplierDTO getById(int id);

    // Update supplier by ID
    boolean update(int id, SupplierDTO dto);

    // Update supplier by ID and garageId
    boolean updateByGarage(int id, Long garageId, SupplierDTO dto);

    // Delete supplier by ID
    boolean delete(int id,Long garageId);
}