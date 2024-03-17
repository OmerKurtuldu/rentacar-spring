package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ModelService;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ModelManager implements ModelService {
    ModelRepository modelRepository;

    @Override
    public Model add(Model model) {
        Model createdModel = modelRepository.save(model);
        return createdModel;
    }

    @Override
    public Model update(Model model) {
        Model existingModel = modelRepository.findById(model.getId()).orElse(null);
        existingModel.setName(model.getName());
        return modelRepository.save(existingModel);
    }

    @Override
    public void delete(int id) {
        modelRepository.deleteById(id);
    }

    @Override
    public List<Model> getAll() {
        List<Model> getAllModel = modelRepository.findAll();
        return getAllModel;
    }

    @Override
    public Model getById(int id) {
        return modelRepository.findById(id).orElse(null);
    }
}
