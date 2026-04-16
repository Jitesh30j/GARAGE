package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.InvoiceRequest;
import com.garage.project.Owner.Entity.Invoice;
import com.garage.project.Owner.Repo.InvoiceRepository;
import com.garage.project.Owner.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repo;

    // CREATE
    @Override
    public Map<String, Object> createInvoice(InvoiceRequest request) {

        Invoice invoice = new Invoice();

        invoice.setJobId(request.getJobId());
        invoice.setGarageId(request.getGarageId());
        invoice.setLabourCost(request.getLabourCost());
        invoice.setPartsCost(request.getPartsCost());

        invoice.calculateTotal();

        repo.save(invoice);

        return success(invoice, "created");
    }

    // GET ALL
    @Override
    public Map<String, Object> getAllByGarage(InvoiceRequest request) {

        List<Invoice> list = repo.findByGarageId(request.getGarageId());

        Map<String, Object> res = new HashMap<>();
        res.put("data", list);
        res.put("status", "success");
        return res;
    }

    // GET SINGLE (GARAGE SAFE)
    @Override
    public Map<String, Object> getSingleInvoice(InvoiceRequest request) {

        Optional<Invoice> opt =
                repo.findByInvoiceIdAndGarageId(request.getInvoiceId(), request.getGarageId());

        if (opt.isEmpty()) return error("invoice not found");

        return success(opt.get(), "found");
    }

    // UPDATE (GARAGE SAFE)
    @Override
    public Map<String, Object> updateInvoice(InvoiceRequest request) {

        Optional<Invoice> opt =
                repo.findByInvoiceIdAndGarageId(request.getInvoiceId(), request.getGarageId());

        if (opt.isEmpty()) return error("invoice not found");

        Invoice invoice = opt.get();

        invoice.setJobId(request.getJobId());
        invoice.setLabourCost(request.getLabourCost());
        invoice.setPartsCost(request.getPartsCost());

        invoice.calculateTotal();

        repo.save(invoice);

        return success(invoice, "updated");
    }

    // DELETE (GARAGE SAFE)
    @Override
    public Map<String, Object> deleteInvoice(InvoiceRequest request) {

        Optional<Invoice> opt =
                repo.findByInvoiceIdAndGarageId(request.getInvoiceId(), request.getGarageId());

        if (opt.isEmpty()) return error("invoice not found");

        repo.delete(opt.get());

        return success(null, "deleted");
    }

    // HELPERS
    private Map<String, Object> success(Object data, String msg) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("message", msg);
        res.put("status", "success");
        return res;
    }

    private Map<String, Object> error(String msg) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", msg);
        res.put("status", "failed");
        return res;
    }
}

