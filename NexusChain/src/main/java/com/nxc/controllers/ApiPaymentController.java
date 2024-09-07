package com.nxc.controllers;

import com.nxc.components.MomoService;
import com.nxc.pojo.Invoice;
import com.nxc.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
@CrossOrigin
public class ApiPaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public String createPayment(@RequestBody Map<String, String> params) throws Exception {
        String orderId = params.get("orderId");
        float amount = Float.parseFloat(params.get("amount"));
        String returnUrl = params.get("returnUrl");

        return paymentService.initiatePayment(orderId, amount, returnUrl);
    }
}