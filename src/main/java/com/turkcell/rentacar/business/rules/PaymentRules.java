package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.adapter.FindexService;
import com.turkcell.rentacar.adapter.pos.PosService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedPaymentRequest;
import com.turkcell.rentacar.business.messages.PaymentMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentRepository;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.enums.CustomerType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentRules {
    PaymentRepository paymentRepository;
    PosService posService;

    public String findCustomerNameByCustomerType(Customer customer) {
        if(findCustomerType(customer).equals(CustomerType.INDIVIDUAL)){
            return customer.getIndividualCustomer().getFirstName() + customer.getIndividualCustomer().getLastName();
        }
        else{
            return customer.getCompanyCustomer().getCompanyName();
        }
    }

    public CustomerType findCustomerType(Customer customer){
        return customer.getType();
    }

    public void checkPayment(CreatedPaymentRequest createdPaymentRequest) {
        boolean paymentControl = posService.pay(createdPaymentRequest.getCardNumber(),
                createdPaymentRequest.getCardHolder(),
                createdPaymentRequest.getCvv(),
                createdPaymentRequest.getExpiryYearDate(),
                createdPaymentRequest.getExpiryMonthDate(),
                createdPaymentRequest.getTotalPrice()
        );

        if(!paymentControl) throw new BusinessException(PaymentMessages.invalidCreditCard);

    }
}
