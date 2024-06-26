package com.turkcell.rentacar.business.rules;


import com.turkcell.rentacar.business.messages.CompanyCustomerMessages;
import com.turkcell.rentacar.business.messages.IndividualCustomerMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.IndividualCustomerRepository;
import com.turkcell.rentacar.entities.concretes.CompanyCustomer;
import com.turkcell.rentacar.entities.concretes.IndividualCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualCustomerBusinessRules {
    private  IndividualCustomerRepository individualCustomerRepository;

    public void individualCustomerIdShouldBeExist(int individualCustomerId) {
        Optional<IndividualCustomer> individualCustomer = individualCustomerRepository.findById(individualCustomerId);
        if (individualCustomer.isEmpty()) {
            throw new BusinessException(IndividualCustomerMessages.individualCustomerNotFound);
        }
    }

}
