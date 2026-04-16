package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.InvoiceRequest;
import com.garage.project.Owner.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody InvoiceRequest request) {
        return service.createInvoice(request);
    }

    @PostMapping("/all")
    public Map<String, Object> all(@RequestBody InvoiceRequest request) {
        return service.getAllByGarage(request);
    }

    @PostMapping("/single")
    public Map<String, Object> single(@RequestBody InvoiceRequest request) {
        return service.getSingleInvoice(request);
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody InvoiceRequest request) {
        return service.updateInvoice(request);
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody InvoiceRequest request) {
        return service.deleteInvoice(request);
    }
}
