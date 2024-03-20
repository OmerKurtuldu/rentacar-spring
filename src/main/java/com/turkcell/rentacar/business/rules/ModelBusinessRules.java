package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    ModelRepository modelRepository;

    public void modelNameCanNotBeDuplicated(String modelName){
        Optional<Model> model = modelRepository.findByNameIgnoreCase(modelName);
        if(model.isPresent()){
            throw new BusinessException("Model already exists!");
        }
    }
}
