package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedMaintenanceResponse;


public interface MaintenanceService {
    CreatedMaintenanceResponse add(CreatedMaintenanceRequest createdMaintenanceRequest);
}
