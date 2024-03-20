package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreatedModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdatedModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.entities.concretes.Model;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreatedModelRequest createdModelRequest);

    UpdatedModelResponse update(UpdatedModelRequest updatedModelRequest);

    void delete(int id);

    List<GetModelResponse> getAll();

    GetModelResponse getById(int id);
}
