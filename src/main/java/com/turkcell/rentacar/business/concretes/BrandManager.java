package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.requests.CreatedBrandRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdatedBrandRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.GetBrandResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedBrandResponse;
import com.turkcell.rentacar.business.rules.BrandBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.dataAccess.abstracts.BrandRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;

    private ModelMapperManager modelMapperService;

    private BrandBusinessRules brandBussinessRules;

    @Override
    public CreatedBrandResponse add(CreatedBrandRequest createBrandRequest) {

        brandBussinessRules.brandNameCanNotBeDuplicated(createBrandRequest.getName());
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = brandRepository.save(brand);

        CreatedBrandResponse createdBrandResponse =
                this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public UpdatedBrandResponse update(UpdatedBrandRequest updatedBrandRequest) {
        brandBussinessRules.brandNameCanNotBeDuplicated(updatedBrandRequest.getName());
        Brand existingBrand = brandRepository.findById(updatedBrandRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found with id: " + updatedBrandRequest.getId()));

        this.modelMapperService.forRequest().map(updatedBrandRequest, existingBrand);

        existingBrand.setUpdatedDate(LocalDateTime.now());
        Brand updatedBrand = brandRepository.save(existingBrand);

        UpdatedBrandResponse updatedBrandResponse =
                this.modelMapperService.forResponse().map(updatedBrand, UpdatedBrandResponse.class);

        return updatedBrandResponse;
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }

    @Override
    public GetBrandResponse getById(int id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        GetBrandResponse getBrandResponse = this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
        return getBrandResponse;
    }

    @Override
    public List<GetBrandResponse> getAll() {
        List<GetBrandResponse> getBrandResponses = new ArrayList<>();
        List<Brand> brands = brandRepository.findAll();
        for (Brand brand : brands) {
            GetBrandResponse brandResponse =
                    this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
            getBrandResponses.add(brandResponse);
        }
        return getBrandResponses;
    }
}
