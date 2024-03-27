package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedRentalResponse;

public interface PaymentService {
    CreatedPaymentResponse add(CreatedPaymentRequest createdPaymentRequest);

}
