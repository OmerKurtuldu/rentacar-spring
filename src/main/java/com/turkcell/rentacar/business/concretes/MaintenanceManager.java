package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.MaintenanceService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedMaintenanceResponse;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.business.rules.MaintenanceBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.MaintenanceRepository;
import com.turkcell.rentacar.entities.concretes.Maintenance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MaintenanceManager implements MaintenanceService {
    private final CarBusinessRules carBusinessRules;
    private final ModelMapperService modelMapperService;
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceBusinessRules maintenanceBusinessRules;

    @Override
    public CreatedMaintenanceResponse add( CreatedMaintenanceRequest createMaintenanceRequest) {

// burada diğer grup araba exist mi diye car business e sordurmuş onu yapalım
        maintenanceBusinessRules.checkIfCarInMaintenance(createMaintenanceRequest.getCarId());

        Maintenance maintenance =this.modelMapperService.forRequest().map(createMaintenanceRequest,Maintenance.class);


        maintenance.setDateSend(LocalDateTime.now());

        this.maintenanceRepository.save(maintenance);

        CreatedMaintenanceResponse createdMaintenanceResponse =
                this.modelMapperService.forResponse().map(maintenance, CreatedMaintenanceResponse.class);

        return createdMaintenanceResponse;
    }

    @Override
    public List<GetAllMaintenanceResponse>  getAll() {
        List<Maintenance> maintenanceList = this.maintenanceRepository.findAll();

        return maintenanceList.stream().map(maintenance -> this.modelMapperService.forResponse().
                map(maintenance, GetAllMaintenanceResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = this.maintenanceRepository.findById(id).orElse(null);
        return modelMapperService.forResponse().map(maintenance, GetMaintenanceResponse.class);
    }


    @Override
    public UpdatedMaintenanceResponse update(UpdatedMaintenanceRequest updateMaintenanceRequest) {
        maintenanceBusinessRules.checkIfMaintenanceExist(updateMaintenanceRequest.getCarId());
        Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
        maintenance.setDateSend(LocalDateTime.now());

        Maintenance updatedMaintenance = maintenanceRepository.save(maintenance);

        return this.modelMapperService.forResponse().map(updatedMaintenance, UpdatedMaintenanceResponse.class);
    }

    @Override
    public void delete(int id) {
        maintenanceBusinessRules.checkIfMaintenanceExist(id);
        Maintenance maintenance = this.maintenanceRepository.findById(id).orElse(null);
        maintenance.setDeletedDate(LocalDateTime.now());
    }
}
