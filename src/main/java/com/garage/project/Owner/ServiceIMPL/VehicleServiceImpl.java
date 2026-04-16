package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.VehicleDTO;
import com.garage.project.Owner.Entity.*;
import com.garage.project.Owner.Repo.*;
import com.garage.project.Owner.Service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepo;
    private final CustomerRepository customerRepo;
    private final GarageRepository garageRepo;

    public VehicleServiceImpl(VehicleRepository vehicleRepo,
                              CustomerRepository customerRepo,
                              GarageRepository garageRepo) {
        this.vehicleRepo = vehicleRepo;
        this.customerRepo = customerRepo;
        this.garageRepo = garageRepo;
    }

    @Override
    public void save(VehicleDTO dto) {

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Garage garage = garageRepo.findById(dto.getGarageId())
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        Vehicle v = new Vehicle();

        v.setBrand(dto.getBrand());
        v.setVehicleModel(dto.getVehicleModel());
        v.setVehicleNumber(dto.getVehicleNumber());
        v.setVehicleName(dto.getVehicleName());
        v.setVehicleType(dto.getVehicleType());
        v.setLastServiceDate(dto.getLastServiceDate());

        v.setCustomer(customer);
        v.setGarage(garage);   // ✅ SAFE (always set here)

        vehicleRepo.save(v);
    }

    @Override
    public List<VehicleDTO> getAllByGarage(Long garageId) {

        List<VehicleDTO> list = new ArrayList<>();

        for (Vehicle v : vehicleRepo.findByGarage_Id(garageId)) {

            VehicleDTO dto = new VehicleDTO();

            dto.setVehicleId(v.getVehicleId());
            dto.setBrand(v.getBrand());
            dto.setVehicleModel(v.getVehicleModel());
            dto.setVehicleNumber(v.getVehicleNumber());
            dto.setVehicleName(v.getVehicleName());
            dto.setVehicleType(v.getVehicleType());
            dto.setLastServiceDate(v.getLastServiceDate());

            if (v.getCustomer() != null)
                dto.setCustomerId(v.getCustomer().getCustomerId());

            if (v.getGarage() != null)
                dto.setGarageId(v.getGarage().getId());

            list.add(dto);
        }

        return list;
    }

    @Override
    public VehicleDTO getByIdAndGarage(Long vehicleId, Long garageId) {

        Vehicle v = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // 🔥 SAFE CHECK (PREVENT NULL CRASH)
        if (v.getGarage() == null) {
            throw new RuntimeException("Vehicle has no garage assigned");
        }

        if (!v.getGarage().getId().equals(garageId)) {
            throw new RuntimeException("Vehicle does not belong to this garage");
        }

        VehicleDTO dto = new VehicleDTO();

        dto.setVehicleId(v.getVehicleId());
        dto.setBrand(v.getBrand());
        dto.setVehicleModel(v.getVehicleModel());
        dto.setVehicleNumber(v.getVehicleNumber());
        dto.setVehicleName(v.getVehicleName());
        dto.setVehicleType(v.getVehicleType());
        dto.setLastServiceDate(v.getLastServiceDate());

        if (v.getCustomer() != null)
            dto.setCustomerId(v.getCustomer().getCustomerId());

        if (v.getGarage() != null)
            dto.setGarageId(v.getGarage().getId());

        return dto;
    }
    @Override
    public void update(Long vehicleId, Long garageId, VehicleDTO dto) {

        Vehicle v = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (v.getGarage() == null) {
            throw new RuntimeException("Vehicle has no garage assigned");
        }

        if (!v.getGarage().getId().equals(garageId)) {
            throw new RuntimeException("Vehicle does not belong to this garage");
        }

        if (dto.getBrand() != null) v.setBrand(dto.getBrand());
        if (dto.getVehicleModel() != null) v.setVehicleModel(dto.getVehicleModel());
        if (dto.getVehicleNumber() != null) v.setVehicleNumber(dto.getVehicleNumber());
        if (dto.getVehicleName() != null) v.setVehicleName(dto.getVehicleName());
        if (dto.getVehicleType() != null) v.setVehicleType(dto.getVehicleType());
        if (dto.getLastServiceDate() != null) v.setLastServiceDate(dto.getLastServiceDate());

        vehicleRepo.save(v);
    }
    @Override
    public void delete(Long vehicleId, Long garageId) {

        Vehicle v = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (v.getGarage() == null) {
            throw new RuntimeException("Vehicle has no garage assigned");
        }

        if (!v.getGarage().getId().equals(garageId)) {
            throw new RuntimeException("Vehicle does not belong to this garage");
        }

        vehicleRepo.delete(v);
    }
}