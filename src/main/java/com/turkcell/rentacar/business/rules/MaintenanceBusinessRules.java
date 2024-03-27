package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.dataAccess.abstracts.MaintenanceRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Maintenance;
import com.turkcell.rentacar.entities.enums.CarState;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.turkcell.rentacar.business.messages.MaintenanceMessages.maintenanceAlreadyExist;


@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository maintenanceRepository;
    private CarRepository carRepository;

    public void checkIfCarInMaintenance(int carId){

        Car car = this.carRepository.findById(carId).orElse(null);
        assert car != null;
        if (!(car.getState() == CarState.MAINTENANCE )){
            throw new BusinessException(maintenanceAlreadyExist);
        }
    }

    public void checkIfMaintenanceExist(int maintenanceId){
        Optional<Maintenance> maintenance = maintenanceRepository.findById(maintenanceId);

        if(!maintenance.isPresent()){
            throw new BusinessException("The maintenance does not exist");
        }
    }
}