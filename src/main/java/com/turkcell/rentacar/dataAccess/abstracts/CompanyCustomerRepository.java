package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.CompanyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyCustomerRepository extends JpaRepository<CompanyCustomer, Integer> {
}
