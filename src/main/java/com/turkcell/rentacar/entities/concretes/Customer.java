package com.turkcell.rentacar.entities.concretes;


import com.turkcell.rentacar.core.entities.BaseEntity;
import com.turkcell.rentacar.entities.enums.CustomerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "type")
    private CustomerType type;

    @OneToOne(mappedBy = "customer")
    private IndividualCustomer individualCustomer;

    @OneToOne(mappedBy = "customer")
    private CompanyCustomer companyCustomer;

}
