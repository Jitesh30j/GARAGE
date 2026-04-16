package com.garage.project.Owner.ServiceIMPL;


import com.garage.project.Owner.Dto.CustomerRequest;
import com.garage.project.Owner.Entity.Customer;
import com.garage.project.Owner.Repo.CustomerRepository;
import com.garage.project.Owner.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // ❌ CREATE FIXED (must use DTO, not only ID)
    @Override
    public Map<String, Object> createCustomer(CustomerRequest request) {

        Map<String, Object> res = new HashMap<>();

        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setVehicleName(request.getVehicleName());
        customer.setVehicleType(request.getVehicleType());
        customer.setUpdatedBy(request.getUpdatedBy());
        customer.setGarageId(request.getGarageId());

        customerRepository.save(customer);

        res.put("code", 200);
        res.put("data", customer);
        res.put("message", "created");
        res.put("status", "success");

        return res;
    }

    // GET ALL
    @Override
    public Map<String, Object> getAllByGarage(CustomerRequest request) {

        Map<String, Object> res = new HashMap<>();

        List<Customer> list = customerRepository.findByGarageId(request.getGarageId());

        res.put("code", 200);
        res.put("data", list);
        res.put("message", "fetch all");
        res.put("status", "success");

        return res;
    }

    // GET SINGLE
    @Override
    public Map<String, Object> getSingleCustomer(CustomerRequest request) {

        Map<String, Object> res = new HashMap<>();

        Customer customer = customerRepository
                .findByCustomerIdAndGarageId(request.getCustomerId(), request.getGarageId());

        if (customer == null) {
            res.put("code", 404);
            res.put("data", "");
            res.put("message", "not found");
            res.put("status", "failed");
            return res;
        }

        res.put("code", 200);
        res.put("data", customer);
        res.put("message", "fetch single");
        res.put("status", "success");

        return res;
    }

    // UPDATE
    @Override
    public Map<String, Object> updateCustomer(CustomerRequest request) {

        Map<String, Object> res = new HashMap<>();

        Customer customer = customerRepository
                .findByCustomerIdAndGarageId(request.getCustomerId(), request.getGarageId());

        if (customer == null) {
            res.put("code", 404);
            res.put("data", "");
            res.put("message", "update failed");
            res.put("status", "failed");
            return res;
        }

        // TODO: real update fields here (not hardcoded)
        customer.setName("Updated Name");

        customerRepository.save(customer);

        res.put("code", 200);
        res.put("data", "");
        res.put("message", "updated");
        res.put("status", "success");

        return res;
    }

    // DELETE
    @Override
    public Map<String, Object> deleteCustomer(CustomerRequest request) {

        Map<String, Object> res = new HashMap<>();

        Customer customer = customerRepository
                .findByCustomerIdAndGarageId(request.getCustomerId(), request.getGarageId());

        if (customer == null) {
            res.put("code", 404);
            res.put("data", "");
            res.put("message", "delete failed");
            res.put("status", "failed");
            return res;
        }

        customerRepository.delete(customer);

        res.put("code", 200);
        res.put("data", "");
        res.put("message", "deleted");
        res.put("status", "success");

        return res;
    }
}