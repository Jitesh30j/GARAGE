package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByGarageId(Integer garageId);

    Optional<Invoice> findByInvoiceIdAndGarageId(Integer invoiceId, Integer garageId);
}
