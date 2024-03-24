package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.business.dtos.requests.create.CreatedModelRequest;
import com.turkcell.rentacar.business.dtos.requests.update.UpdatedModelRequest;
import com.turkcell.rentacar.business.dtos.responses.create.CreatedModelResponse;
import com.turkcell.rentacar.business.dtos.responses.get.GetModelResponse;
import com.turkcell.rentacar.business.dtos.responses.update.UpdatedModelResponse;
import com.turkcell.rentacar.business.rules.ModelBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    ModelRepository modelRepository;
    ModelMapperManager modelMapperService;
    ModelBusinessRules modelBusinessRules;

    @Override
    public CreatedModelResponse add(CreatedModelRequest createdModelRequest) {
        modelBusinessRules.modelNameCanNotBeDuplicated(createdModelRequest.getName());

        Model createdModel = this.modelMapperService.forRequest().map(createdModelRequest, Model.class);
        createdModel.setCreatedDate(LocalDateTime.now());
        modelRepository.save(createdModel);

        CreatedModelResponse createdModelResponse = this.modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public UpdatedModelResponse update(UpdatedModelRequest updatedModelRequest) {
        modelBusinessRules.modelNameCanNotBeDuplicated(updatedModelRequest.getName());
        Model existingModel = modelMapperService.forRequest().map(updatedModelRequest, Model.class);
        existingModel.setUpdatedDate(LocalDateTime.now());
        Model updatedModel = modelRepository.save(existingModel);

        UpdatedModelResponse updatedModelResponse = modelMapperService.forResponse().map(updatedModel, UpdatedModelResponse.class);
        return updatedModelResponse;
    }

    @Override
    public void delete(int id) {
        modelRepository.deleteById(id);
    }

    @Override
    public GetModelResponse getById(int id) {
        Model model = modelRepository.findById(id).orElse(null);
        GetModelResponse getModelResponse = this.modelMapperService.forResponse().map(model, GetModelResponse.class);
        return getModelResponse;
    }

    @Override
    public List<GetModelResponse> getAll() {
        List<GetModelResponse> modelResponses = new ArrayList<>();
        List<Model> models = modelRepository.findAll();
        for (Model model : models) {
            GetModelResponse modelResponse =
                    this.modelMapperService.forResponse().map(model, GetModelResponse.class);
            modelResponses.add(modelResponse);
        }
        return modelResponses;
    }
}
