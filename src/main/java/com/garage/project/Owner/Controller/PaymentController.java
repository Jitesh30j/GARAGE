package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.PaymentDTO;
import com.garage.project.Owner.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // 1️⃣ POST: Add payment
    @PostMapping
    public Map<String, Object> save(@RequestBody PaymentDTO dto) {
        service.save(dto);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Payment added");
        res.put("data", " ");

        return res;
    }

    // 2️⃣ GET: All payments
    @PostMapping("/getall")
    public Map<String, Object> getAll(@RequestBody Map<String, Long> req) {

        Long garageId = req.get("garageId");

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Payment list");
        res.put("data", service.getAll(garageId));

        return res;
    }

    // 3️⃣ GET: Payment by ID
    @PostMapping("/getbyid")
    public Map<String, Object> getPayment(@RequestBody PaymentDTO request) {

        Map<String, Object> res = new HashMap<>();

        try {
            PaymentDTO dto = service.getByIdAndGarage(
                    request.getPaymentId(),
                    request.getGarageId()
            );

            res.put("status", "success");
            res.put("code", 200);
            res.put("message", "Payment found");
            res.put("data", dto);

        } catch (RuntimeException e) {
            res.put("status", "error");
            res.put("code", 404);
            res.put("message", e.getMessage());
            res.put("data", " ");
        }

        return res;
    }

    // 4️⃣ UPDATE PAYMENT
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody PaymentDTO dto) {

        Map<String, Object> res = new HashMap<>();

        try {
            service.update(
                    dto.getPaymentId(),
                    dto.getGarageId(),
                    dto
            );

            res.put("status", "success");
            res.put("code", 200);
            res.put("message", "updated successful");
            res.put("data", " ");

        } catch (RuntimeException e) {
            res.put("status", "error");
            res.put("code", 404);
            res.put("message", e.getMessage());
            res.put("data", " ");
        }

        return res;
    }

    // 5️⃣ DELETE PAYMENT
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody PaymentDTO dto) {

        Map<String, Object> res = new HashMap<>();

        try {
            service.delete(
                    dto.getPaymentId(),
                    dto.getGarageId()
            );

            res.put("status", "success");
            res.put("code", 200);
            res.put("message", "Payment deleted");
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