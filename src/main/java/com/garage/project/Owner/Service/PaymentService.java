package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.PaymentDTO;
import java.util.List;

public interface PaymentService {

    void save(PaymentDTO dto);

    List<PaymentDTO> getAll(Long garageId);

    PaymentDTO getByIdAndGarage(int paymentId, Long garageId);

    void update(int paymentId, Long garageId, PaymentDTO dto);

    void delete(int paymentId, Long garageId);

    List<PaymentDTO> getByGarage(Long garageId);
}