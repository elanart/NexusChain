package com.nxc.service.impl;

import com.nxc.pojo.Invoice;
import com.nxc.pojo.Order;
import com.nxc.repository.InvoiceRepository;
import com.nxc.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Override
    public void createInvoice(Order order, BigDecimal totalAmount) {
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setInvoiceDate(new Date());
        invoice.setTotalAmount(totalAmount);
        invoice.setIsPaid(false);

        invoiceRepository.save(invoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.update(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id);
        invoiceRepository.delete(invoice);
    }

    @Override
    public void updateInvoiceStatus(Long orderId, boolean isPaid) {
        Invoice invoice = invoiceRepository.findInvoiceByOrderId(orderId);
        if (invoice != null) {
            invoice.setIsPaid(isPaid);
            invoiceRepository.update(invoice);
        }
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getInvoicesByOrder(Order order) {
        return invoiceRepository.findByOrder(order);
    }

    @Override
    public List<Invoice> getInvoicesByIsPaid(Boolean isPaid) {
        return invoiceRepository.findByIsPaid(isPaid);
    }
}