package com.nxc.service.impl;

import com.nxc.components.MomoService;
import com.nxc.pojo.Invoice;
import com.nxc.pojo.Order;
import com.nxc.repository.OrderRepository;
import com.nxc.service.InvoiceService;
import com.nxc.service.OrderService;
import com.nxc.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final MomoService momoService;
    private final InvoiceService invoiceService;
    private final OrderRepository orderRepository;

    @Override
    public String initiatePayment(String orderId, float amount, String returnUrl) throws Exception {
        String momoResponse = momoService.createPaymentRequest(orderId, amount, returnUrl);

        Long order_id = Long.parseLong(orderId);
        Order order = orderRepository.findById(order_id);
        BigDecimal totalAmount = BigDecimal.valueOf(amount);
        invoiceService.createInvoice(order, totalAmount);

        return momoResponse;
    }

    @Override
    public void handlePaymentCallback(Long orderId, String status) {
        Invoice invoice = invoiceService.getInvoiceById(orderId);
        if (invoice != null) {
            boolean isPaid = "SUCCESS".equalsIgnoreCase(status);
            invoiceService.updateInvoiceStatus(orderId, isPaid);
        }
    }
}