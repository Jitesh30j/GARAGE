package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.InvoiceRequest;

import java.util.Map;

public interface InvoiceService {

    Map<String, Object> createInvoice(InvoiceRequest request);

    Map<String, Object> getAllByGarage(InvoiceRequest request);

    Map<String, Object> getSingleInvoice(InvoiceRequest request);

    Map<String, Object> updateInvoice(InvoiceRequest request);

    Map<String, Object> deleteInvoice(InvoiceRequest request);
}
