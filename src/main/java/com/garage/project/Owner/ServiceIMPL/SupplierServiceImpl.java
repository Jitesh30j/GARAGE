package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.SupplierDTO;
import com.garage.project.Owner.Entity.Garage;
import com.garage.project.Owner.Entity.Supplier;
import com.garage.project.Owner.Repo.GarageRepository;
import com.garage.project.Owner.Repo.SupplierRepository;
import com.garage.project.Owner.Service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repo;
    private final GarageRepository garageRepo;

    public SupplierServiceImpl(SupplierRepository repo, GarageRepository garageRepo) {
        this.repo = repo;
        this.garageRepo = garageRepo;
    }

    // ---------------- SAVE ----------------
    @Override
    public void save(SupplierDTO dto) {

        if (dto.getGarageId() == null) {
            throw new RuntimeException("GarageId is required");
        }

        Garage g = garageRepo.findById(dto.getGarageId())
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        Supplier s = new Supplier();
        s.setSupplierName(dto.getSupplierName());
        s.setEmail(dto.getEmail());
        s.setPhone(dto.getPhone());
        s.setAddress(dto.getAddress());
        s.setSupplierGst(dto.getSupplierGst());
        s.setGarage(g);

        repo.save(s);
    }

    // ---------------- GET BY GARAGE ----------------
    @Override
    public List<SupplierDTO> getByGarageId(Long garageId) {   // FIXED

        List<SupplierDTO> list = new ArrayList<>();

        for (Supplier s : repo.findByGarage_Id(garageId)) {
            list.add(mapToDTO(s));
        }

        return list;
    }

    // ---------------- GET BY SUPPLIER + GARAGE ----------------
    @Override
    public SupplierDTO getByGarageAndSupplier(int supplierId, Long garageId) {  // FIXED

        Supplier s = repo.findBySupplierIdAndGarage_Id(supplierId, garageId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        return mapToDTO(s);
    }

    // ---------------- UPDATE ----------------
    @Override
    public boolean updateByGarage(int supplierId, Long garageId, SupplierDTO dto) { // FIXED

        Optional<Supplier> opt = repo.findBySupplierIdAndGarage_Id(supplierId, garageId);

        if (opt.isPresent()) {
            Supplier s = opt.get();

            if (dto.getSupplierName() != null) s.setSupplierName(dto.getSupplierName());
            if (dto.getEmail() != null) s.setEmail(dto.getEmail());
            if (dto.getPhone() != null) s.setPhone(dto.getPhone());
            if (dto.getAddress() != null) s.setAddress(dto.getAddress());
            if (dto.getSupplierGst() != null) s.setSupplierGst(dto.getSupplierGst());

            repo.save(s);
            return true;
        }
        return false;
    }

    // ---------------- DELETE ----------------
    @Override
    public boolean delete(int supplierId, Long garageId) { // FIXED

        Optional<Supplier> opt = repo.findBySupplierIdAndGarage_Id(supplierId, garageId);

        if (opt.isPresent()) {
            repo.delete(opt.get());
            return true;
        }
        return false;
    }

    // ---------------- MAPPER ----------------
    private SupplierDTO mapToDTO(Supplier s) {

        SupplierDTO dto = new SupplierDTO();

        dto.setSupplierId(s.getSupplierId());
        dto.setSupplierName(s.getSupplierName());
        dto.setEmail(s.getEmail());
        dto.setPhone(s.getPhone());
        dto.setAddress(s.getAddress());
        dto.setSupplierGst(s.getSupplierGst());

        dto.setGarageId(
                s.getGarage() != null ? s.getGarage().getId() : null
        );

        return dto;
    }

    // ---------------- UNUSED METHODS ----------------
    @Override public List<SupplierDTO> getAll() { return null; }
    @Override public SupplierDTO getById(int id) { return null; } // FIXED
    @Override public boolean update(int id, SupplierDTO dto) { return false; } // FIXED
}