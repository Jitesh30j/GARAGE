package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.CustomerRequest;
import com.garage.project.Owner.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ✅ FIXED CREATE
    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody CustomerRequest request) {
        return customerService.createCustomer(request);
    }

    // GET ALL
    @PostMapping("/all")
    public Map<String, Object> getAll(@RequestBody CustomerRequest request) {
        return customerService.getAllByGarage(request);
    }

    // GET SINGLE
    @PostMapping("/single")
    public Map<String, Object> getSingle(@RequestBody CustomerRequest request) {
        return customerService.getSingleCustomer(request);
    }

    // UPDATE
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody CustomerRequest request) {
        return customerService.updateCustomer(request);
    }

    // DELETE
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody CustomerRequest request) {
        return customerService.deleteCustomer(request);
    }
}