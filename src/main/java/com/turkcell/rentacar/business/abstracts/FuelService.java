package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedFuelResponse;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreatedFuelRequest createdFuelRequest);

    UpdatedFuelResponse update(UpdatedFuelRequest updatedFuelRequest);

    void delete(int id);

    List<GetFuelResponse> getAll();

    GetFuelResponse getById(int id);
}
