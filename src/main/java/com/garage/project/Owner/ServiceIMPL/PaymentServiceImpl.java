package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.PaymentDTO;
import com.garage.project.Owner.Entity.Payment;
import com.garage.project.Owner.Repo.PaymentRepository;
import com.garage.project.Owner.Service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;

    public PaymentServiceImpl(PaymentRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(PaymentDTO dto) {

        Payment p = new Payment();
        p.setAmount(dto.getAmount());
        p.setPaymentMethod(dto.getPaymentMethod());
        p.setPaymentDate(dto.getPaymentDate());
        p.setInvoiceId(dto.getInvoiceId());
        p.setGarageId(dto.getGarageId());

        repo.save(p);
    }

    @Override
    public List<PaymentDTO> getAll(Long garageId) {

        List<PaymentDTO> list = new ArrayList<>();

        for (Payment p : repo.findByGarageId(garageId)) {

            PaymentDTO dto = new PaymentDTO();

            dto.setPaymentId(p.getPaymentId());
            dto.setAmount(p.getAmount());
            dto.setPaymentMethod(p.getPaymentMethod());
            dto.setPaymentDate(p.getPaymentDate());
            dto.setGarageId(p.getGarageId());
            dto.setInvoiceId(p.getInvoiceId());

            list.add(dto);
        }

        return list;
    }

    @Override
    public PaymentDTO getByIdAndGarage(int paymentId, Long garageId) {

        Payment p = repo.findByPaymentIdAndGarageId(paymentId, garageId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        PaymentDTO dto = new PaymentDTO();

        dto.setPaymentId(p.getPaymentId());
        dto.setAmount(p.getAmount());
        dto.setPaymentMethod(p.getPaymentMethod());
        dto.setPaymentDate(p.getPaymentDate());
        dto.setGarageId(p.getGarageId());
        dto.setInvoiceId(p.getInvoiceId());

        return dto;
    }

    @Override
    public void update(int paymentId, Long garageId, PaymentDTO dto) {

        Payment p = repo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (!p.getGarageId().equals(garageId)) {
            throw new RuntimeException("Not belongs to garage");
        }

        if (dto.getAmount() != null) p.setAmount(dto.getAmount());
        if (dto.getPaymentMethod() != null) p.setPaymentMethod(dto.getPaymentMethod());
        if (dto.getPaymentDate() != null) p.setPaymentDate(dto.getPaymentDate());
        if (dto.getInvoiceId() != null) p.setInvoiceId(dto.getInvoiceId());

        repo.save(p);
    }

    @Override
    public void delete(int paymentId, Long garageId) {

        Payment p = repo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (!p.getGarageId().equals(garageId)) {
            throw new RuntimeException("Not belongs to garage");
        }

        repo.delete(p);
    }

    @Override
    public List<PaymentDTO> getByGarage(Long garageId) {
        return getAll(garageId);
    }
}