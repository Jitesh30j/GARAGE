package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.SupplierDTO;
import com.garage.project.Owner.Service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    // ---------------- CREATE ----------------
    @PostMapping("/add")
    public Map<String, Object> save(@RequestBody SupplierDTO dto) {
        service.save(dto);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Supplier added successfully");
        res.put("data", " ");
        return res;
    }

    // ---------------- GET ALL BY GARAGE ----------------
    @PostMapping("/getall")
    public Map<String, Object> getAll(@RequestBody Map<String, Long> req) {  // FIXED
        Long garageId = req.get("garageId");

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Supplier list fetched successfully");
        res.put("data", service.getByGarageId(garageId));
        return res;
    }

    // ---------------- GET SINGLE ----------------
    @PostMapping("/getbyid")
    public Map<String, Object> getSingle(@RequestBody Map<String, Long> req) { // FIXED
        int supplierId = Math.toIntExact(req.get("supplierId"));
        Long garageId = req.get("garageId");

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Supplier fetched successfully");
        res.put("data", service.getByGarageAndSupplier(supplierId, garageId));
        return res;
    }

    // ---------------- UPDATE ----------------
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody SupplierDTO dto) {
        boolean updated = service.updateByGarage(dto.getSupplierId(), dto.getGarageId(), dto);

        Map<String, Object> res = new HashMap<>();
        res.put("status", updated ? "success" : "error");
        res.put("code", updated ? 200 : 404);
        res.put("message", updated ? "Supplier updated successfully" : "Supplier not found");
        res.put("data", " ");
        return res;
    }

    // ---------------- DELETE ----------------
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Long> req) { // FIXED
        int  supplierId = Math.toIntExact(req.get("supplierId"));
        Long garageId = req.get("garageId");

        boolean deleted = service.delete(supplierId, garageId);

        Map<String, Object> res = new HashMap<>();
        res.put("status", deleted ? "success" : "error");
        res.put("code", deleted ? 200 : 404);
        res.put("message", deleted ? "Supplier deleted successfully" : "Supplier not found");
        res.put("data", " ");
        return res;
    }
}