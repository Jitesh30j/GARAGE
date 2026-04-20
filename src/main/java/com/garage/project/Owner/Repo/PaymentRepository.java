package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByGarageId(Long garageId);

    Optional<Payment> findByPaymentIdAndGarageId(int paymentId, Long garageId);
}