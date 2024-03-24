package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedCarRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedCarResponse;

public interface CarService {
    CreatedCarResponse add(CreatedCarRequest createCarRequest);
}
