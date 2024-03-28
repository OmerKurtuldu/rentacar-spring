package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedPaymentRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedPaymentResponse;
import com.turkcell.rentacar.business.rules.PaymentRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerRepository;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public UpdatedPaymentResponse update(UpdatedPaymentRequest updatedPaymentRequest) {


        Payment existingPayment = modelMapperService.forRequest().map(updatedPaymentRequest, Payment.class);
        existingPayment.setUpdatedDate(LocalDateTime.now());

        UpdatedPaymentResponse updatedPaymentResponse = modelMapperService.forResponse().map(existingPayment, UpdatedPaymentResponse.class);

        Rental rental = rentalRepository.findById(existingPayment.getRental().getId()).orElse(null);

        Customer customer = customerRepository.findById(rental.getCustomer().getId()).orElse(null);

        updatedPaymentResponse.setCustomerName(paymentRules.findCustomerNameByCustomerType(customer));
        updatedPaymentResponse.setCreatedDate(LocalDateTime.now());
        updatedPaymentResponse.setPaymentDate(LocalDateTime.now());

        paymentRepository.save(existingPayment);

        return updatedPaymentResponse;
    }

    @Override
    public void delete(int id) {
        Optional<Payment> foundOptionalPayment = paymentRepository.findById(id);
        paymentRules.paymentShouldBeExist(foundOptionalPayment);
        paymentRepository.delete(foundOptionalPayment.get());
    }

    @Override
    public List<GetAllPaymentResponse> getAll() {
        List<GetAllPaymentResponse> paymentResponses = new ArrayList<>();
        List<Payment> payments = paymentRepository.findAll();
        for (Payment payment : payments) {
            GetAllPaymentResponse paymentResponse =
                    this.modelMapperService.forResponse().map(payment, GetAllPaymentResponse.class);
            paymentResponses.add(paymentResponse);
        }
        return paymentResponses;
    }

    @Override
    public GetPaymentResponse getById(int id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        GetPaymentResponse getPaymentResponse = this.modelMapperService.forResponse().map(payment, GetPaymentResponse.class);
        return getPaymentResponse;
    }
}
