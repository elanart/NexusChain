package com.nxc.repository;

import com.nxc.pojo.Invoice;
import com.nxc.pojo.Order;

import java.util.List;

public interface InvoiceRepository {
    void save(Invoice invoice);
    void update(Invoice invoice);
    void delete(Invoice invoice);
    Invoice findById(Long id);
    Invoice findInvoiceByOrderId(Long orderId);
    List<Invoice> findAll();
    List<Invoice> findByOrder(Order order);
    List<Invoice> findByIsPaid(Boolean isPaid);
}