package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.CreatedBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdatedBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedBrandResponse;
import com.turkcell.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreatedBrandRequest createBrandRequest);

    UpdatedBrandResponse update(UpdatedBrandRequest updatedBrandRequest);

    void delete(int id);

    GetBrandResponse getById(int id);

    List<GetBrandResponse> getAll();

}

