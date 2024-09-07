package com.nxc.service;

public interface PaymentService {
    String initiatePayment(String orderId, float amount, String returnUrl) throws Exception;
    void handlePaymentCallback(Long orderId, String status);
}