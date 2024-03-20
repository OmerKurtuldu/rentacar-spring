package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdatedTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.GetTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedTransmissionResponse;

import java.util.List;

public interface TransmissionService {
    CreatedTransmissionResponse add(CreatedTransmissionRequest createdTransmissionRequest);

    UpdatedTransmissionResponse update(UpdatedTransmissionRequest updatedTransmissionRequest);

    void delete(int id);

    List<GetTransmissionResponse> getAll();

    GetTransmissionResponse getById(int id);
}
