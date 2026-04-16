package com.garage.project.Owner.Service;
import com.garage.project.Owner.Dto.CustomerRequest;


import java.util.Map;

public interface CustomerService {
    Map<String, Object> createCustomer(CustomerRequest request);

    Map<String, Object> getAllByGarage(CustomerRequest request);

    Map<String, Object> getSingleCustomer(CustomerRequest request);

    Map<String, Object> updateCustomer(CustomerRequest request);

    Map<String, Object> deleteCustomer(CustomerRequest request);
}