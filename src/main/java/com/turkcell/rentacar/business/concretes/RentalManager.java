package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.getAll.GetAllRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedRentalResponse;
import com.turkcell.rentacar.business.rules.RentalBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private RentalBusinessRules rentalBusinessRules;


    @Override
    public CreatedRentalResponse add(CreatedRentalRequest createdRentalRequest) {
        Rental createdRental = this.modelMapperService.forRequest().map(createdRentalRequest, Rental.class);
        createdRental.setCreatedDate(LocalDateTime.now());
        rentalRepository.save(createdRental);

        CreatedRentalResponse createdRentalResponse =
                this.modelMapperService.forResponse().map(createdRental, CreatedRentalResponse.class);
        return createdRentalResponse;
    }

    @Override
    public UpdatedRentalResponse update(UpdatedRentalRequest updatedRentalRequest) {
        Optional<Rental> rentalOptional = rentalRepository.findById(updatedRentalRequest.getId());

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            // Güncelleme isteğiyle gelen bilgileri mevcut yakıt nesnesini aktarma
            this.modelMapperService.forRequest().map(updatedRentalRequest, rental);
            rental.setUpdatedDate(LocalDateTime.now());

            Rental updatedRental = rentalRepository.save(rental);

            // Güncellenmiş yakıt nesnesini CreatedFuelResponse nesnesine dönüştür
            UpdatedRentalResponse updatedRentalResponse =
                    this.modelMapperService.forResponse().map(updatedRental, UpdatedRentalResponse.class);

            return updatedRentalResponse;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public GetRentalResponse getById(int id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        Rental rental = rentalOptional.get();
        GetRentalResponse getRentalResponse =
                this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
        return getRentalResponse;
    }

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalResponse> rentalResponses = new ArrayList<>();

        for (Rental rental : rentals) {
            GetAllRentalResponse getAllRentalResponse =
                    this.modelMapperService.forResponse().map(rental, GetAllRentalResponse.class);
            rentalResponses.add(getAllRentalResponse);
        }
        return rentalResponses;
    }
}
