package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class TransmissionManager  implements TransmissionService {
    private TransmissionRepository transmissionRepository;
    @Override
    public Transmission add(Transmission transmission) {
        Transmission creatTransmission = transmissionRepository.save(transmission);
        return creatTransmission;
    }

    @Override
    public Transmission update(Transmission transmission) {
        Transmission existingTransmissiom = transmissionRepository.findById(transmission.getId()).orElse(null);
        existingTransmissiom.setName(transmission.getName());
        return transmissionRepository.save(existingTransmissiom);
    }

    @Override
    public void delete(int id) {
        Transmission existingTransmissiom = transmissionRepository.findById(id).orElse(null);
        transmissionRepository.delete(existingTransmissiom);
    }

    @Override
    public List<Transmission> getAll() {
        return transmissionRepository.findAll();
    }

    @Override
    public Transmission getById(int id) {
        return transmissionRepository.findById(id).orElse(null);
    }
}
