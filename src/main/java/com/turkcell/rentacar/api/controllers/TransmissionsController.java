package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmissions")
public class TransmissionsController {
    TransmissionService transmissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transmission add(@RequestBody Transmission transmission) {
        return transmissionService.add(transmission);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Transmission update(@RequestBody Transmission transmission) {
        return transmissionService.update(transmission);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        transmissionService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transmission> getAll() {
        return transmissionService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transmission getById(@PathVariable int id) {
        return transmissionService.getById(id);
    }
}
