package com.nxc.service;

import com.nxc.pojo.Invoice;
import com.nxc.pojo.Order;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {
    void createInvoice(Order order, BigDecimal totalAmount);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(Long id);
    void updateInvoiceStatus(Long orderId, boolean isPaid);
    Invoice getInvoiceById(Long id);
    List<Invoice> getAllInvoices();
    List<Invoice> getInvoicesByOrder(Order order);
    List<Invoice> getInvoicesByIsPaid(Boolean isPaid);
}