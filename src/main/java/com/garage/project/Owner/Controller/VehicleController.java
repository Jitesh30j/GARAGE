package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.VehicleDTO;
import com.garage.project.Owner.Service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    // 1️⃣ Add Vehicle
    @PostMapping("/add")
    public Map<String, Object> addVehicle(@RequestBody VehicleDTO dto) {
        service.save(dto);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Vehicle added");
        res.put("data", " ");

        return res;
    }

    // 2️⃣ Get All Vehicles by Garage
    @PostMapping("/getall")
    public Map<String, Object> getAll(@RequestBody Map<String, Long> req) {   // FIXED
        Long garageId = req.get("garageId");

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Vehicle list");
        res.put("data", service.getAllByGarage(garageId));

        return res;
    }

    // 3️⃣ Get Vehicle by ID + Garage
    @PostMapping("/getbyid")
    public Map<String, Object> getVehicle(@RequestBody VehicleDTO dto) {
        Map<String, Object> res = new HashMap<>();
        try {
            VehicleDTO vehicle = service.getByIdAndGarage(dto.getVehicleId(), dto.getGarageId());

            res.put("status", "success");
            res.put("code", 200);
            res.put("message", "Vehicle found");
            res.put("data", vehicle);

        } catch (RuntimeException e) {
            res.put("status", "error");
            res.put("code", 404);
            res.put("message", e.getMessage());
            res.put("data", " ");
        }
        return res;
    }

    // 4️⃣ Update Vehicle
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody VehicleDTO dto) {
        Map<String, Object> res = new HashMap<>();
        try {
            service.update(dto.getVehicleId(), dto.getGarageId(), dto);

            res.put("status", "success");
            res.put("code", 200);
            res.put("message", "Vehicle updated");
            res.put("data", " ");

        } catch (RuntimeException e) {
            res.put("status", "error");
            res.put("code", 404);
            res.put("message", e.getMessage());
            res.put("data", " ");
        }
        return res;
    }

    // 5️⃣ Delete Vehicle
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody VehicleDTO dto) {
        Map<String, Object> res = new HashMap<>();
        try {
            service.delete(dto.getVehicleId(), dto.getGarageId());

            res.put("status", "success");
            res.put("code", 200);
            res.put("message", "Vehicle deleted");
            res.put("data", " ");

        } catch (RuntimeException e) {
            res.put("status", "error");
            res.put("code", 404);
            res.put("message", e.getMessage());
            res.put("data", " ");
        }
        return res;
    }
}