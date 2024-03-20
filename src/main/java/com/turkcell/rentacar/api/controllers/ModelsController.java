package com.turkcell.rentacar.api.controllers;


import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.CreatedModelRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdatedModelRequest;
import com.turkcell.rentacar.business.dtos.responses.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedModelResponse;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelsController {
    ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@RequestBody CreatedModelRequest createdModelRequest) {
        return modelService.add(createdModelRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedModelResponse update(@RequestBody UpdatedModelRequest updatedModelRequest) {
        return modelService.update(updatedModelRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        modelService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetModelResponse getById(int id) {
        return modelService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetModelResponse> getAll() {
        return modelService.getAll();
    }

}
