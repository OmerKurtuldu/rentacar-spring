package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedPaymentResponse;
import com.turkcell.rentacar.business.rules.PaymentRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerRepository;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    PaymentRepository paymentRepository;
    ModelMapperService modelMapperService;
    CustomerRepository customerRepository;
    PaymentRules paymentRules;
    RentalRepository rentalRepository;


    @Override
    public CreatedPaymentResponse add(CreatedPaymentRequest createdPaymentRequest) {
        paymentRules.checkPayment(createdPaymentRequest);

        Payment payment = this.modelMapperService.forRequest().map(createdPaymentRequest, Payment.class);

        paymentRepository.save(payment);
        Rental rental = rentalRepository.findById(payment.getRental().getId()).orElse(null);

        Customer customer = customerRepository.findById(rental.getCustomer().getId()).orElse(null);

        CreatedPaymentResponse createdPaymentResponse =this.modelMapperService.forResponse().map(payment, CreatedPaymentResponse.class);

        createdPaymentResponse.setCustomerName(paymentRules.findCustomerNameByCustomerType(customer));
        createdPaymentResponse.setCreatedDate(LocalDateTime.now());
        createdPaymentResponse.setPaymentDate(LocalDateTime.now());

        return createdPaymentResponse;
    }
}
