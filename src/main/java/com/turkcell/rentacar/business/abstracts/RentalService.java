package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.create.CreatedBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedBrandResponse;

import java.util.List;

public interface RentalService {
    CreatedRentalResponse add(CreatedRentalRequest createdRentalRequest);

    UpdatedBrandResponse update(UpdatedBrandRequest updatedBrandRequest);

    void delete(int id);

    GetBrandResponse getById(int id);
      //mhmtgks
    List<GetBrandResponse> getAll();
}
