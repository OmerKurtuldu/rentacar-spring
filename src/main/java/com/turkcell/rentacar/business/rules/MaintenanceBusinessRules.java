package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.dataAccess.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository maintenanceRepository;

}