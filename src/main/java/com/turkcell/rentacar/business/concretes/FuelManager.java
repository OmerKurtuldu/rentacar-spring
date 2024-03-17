package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.FuelService;

import com.turkcell.rentacar.dataAccess.abstracts.FuelRepository;
import com.turkcell.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class FuelManager implements FuelService {
    private FuelRepository fuelRepository;

    @Override
    public Fuel add(Fuel fuel) {
        Fuel createdFuel = fuelRepository.save(fuel);
        return createdFuel;
    }

    @Override
    public Fuel update(Fuel fuel) {
        Fuel existingFuel = fuelRepository.findById(fuel.getId()).orElse(null);
        existingFuel.setName(fuel.getName());
        existingFuel.setUpdatedDate(LocalDateTime.now());
        return fuelRepository.save(existingFuel);
    }

    @Override
    public void delete(int id) {
        fuelRepository.deleteById(id);
    }

    @Override
    public List<Fuel> getAll() {
        List<Fuel> getAllFuel = fuelRepository.findAll();
        return getAllFuel;
    }

    @Override
    public Fuel getById(int id) {
        return fuelRepository.findById(id).orElse(null);
    }
}
