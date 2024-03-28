package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CompanyCustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/companyCustomers")
public class CompanyCustomersController {
    private CompanyCustomerService companyCustomerService;


}
