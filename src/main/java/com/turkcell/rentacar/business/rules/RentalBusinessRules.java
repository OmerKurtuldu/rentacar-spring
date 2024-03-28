package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.messages.CompanyCustomerMessages;
import com.turkcell.rentacar.business.messages.RentalMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CarRepository;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    RentalRepository rentalRepository;
    CarRepository carRepository;
    public void rentalShouldBeExist(int rentalId) {
        Optional<Rental> rental = rentalRepository.findById(rentalId);
        if (rental.isEmpty()) {
            throw new BusinessException(RentalMessages.rentalNotFound);
        }
    }

    public double calculateTotalPrice(Rental rental) {
        int days = (int) ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        Car car = this.carRepository.findById(rental.getCar().getId()).orElse(null);
        double result = car.getDailyPrice() * days;
        List<AdditionalFeature> additioanalFeatures = rental.getAdditionalFeatures();
        //TODO burada kontrol methoda çekilebilir.
        if(!additioanalFeatures.isEmpty()){
            for(var additioanalFeature : additioanalFeatures){
                result += additioanalFeature.getFeaturePrice();
            }
        }
        return result;
    }
}
