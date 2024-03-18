package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreatedFuelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdatedFuelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetFuelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedFuelResponse;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreatedFuelRequest createdFuelRequest);

    UpdatedFuelResponse update(UpdatedFuelRequest updatedFuelRequest);

    void delete(int id);

    List<GetFuelResponse> getAll();

    GetFuelResponse getById(int id);
}
