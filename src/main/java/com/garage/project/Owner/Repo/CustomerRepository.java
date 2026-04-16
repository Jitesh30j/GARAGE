package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByGarageId(Integer garageId);

    Customer findByCustomerIdAndGarageId(Integer customerId, Integer garageId);
}