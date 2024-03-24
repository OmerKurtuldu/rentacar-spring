package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.FuelService;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedFuelResponse;
import com.turkcell.rentacar.business.rules.FuelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;

    private ModelMapperManager modelMapperService;

    private FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse add(CreatedFuelRequest createdFuelRequest) {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(createdFuelRequest.getName());
        Fuel createdFuel = this.modelMapperService.forRequest().map(createdFuelRequest, Fuel.class);
        createdFuel.setCreatedDate(LocalDateTime.now());
        fuelRepository.save(createdFuel);

        CreatedFuelResponse createdFuelResponse = this.modelMapperService.forResponse()
                .map(createdFuel, CreatedFuelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public UpdatedFuelResponse update(UpdatedFuelRequest updatedFuelRequest) {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(updatedFuelRequest.getName());
        Fuel existingFuel = fuelRepository.findById(updatedFuelRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Fuel not found with id: " + updatedFuelRequest.getId()));

        this.modelMapperService.forRequest().map(updatedFuelRequest, existingFuel);
        existingFuel.setUpdatedDate(LocalDateTime.now());
        Fuel updatedFuel = fuelRepository.save(existingFuel);

        UpdatedFuelResponse updatedFuelResponse =
                this.modelMapperService.forResponse().map(updatedFuel, UpdatedFuelResponse.class);

        return updatedFuelResponse;
    }

    @Override
    public void delete(int id) {
        fuelRepository.deleteById(id);
    }

    @Override
    public GetFuelResponse getById(int id) {
        Fuel fuel = fuelRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Fuel not foun with id: " + id));
        GetFuelResponse getFuelResponse =
                this.modelMapperService.forResponse().map(fuel, GetFuelResponse.class);
        return getFuelResponse;
    }

    @Override
    public List<GetFuelResponse> getAll() {
        List<GetFuelResponse> fuelResponses = new ArrayList<>();
        List<Fuel> fuels = fuelRepository.findAll();
        for (Fuel fuel : fuels) {
            GetFuelResponse fuelResponse =
                    this.modelMapperService.forResponse().map(fuel, GetFuelResponse.class);
            fuelResponses.add(fuelResponse);
        }
        return fuelResponses;
    }
}
