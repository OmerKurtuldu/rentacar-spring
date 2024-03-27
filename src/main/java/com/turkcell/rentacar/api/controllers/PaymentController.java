package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedPaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPaymentResponse add(@RequestBody CreatedPaymentRequest createdPaymentRequest) {
        return paymentService.add(createdPaymentRequest);
    }
}
