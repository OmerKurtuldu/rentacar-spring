package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandsController {
    private BrandService brandService; //IoC

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand add(@RequestBody Brand brand) {
        return brandService.add(brand);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Brand update(@RequestBody Brand brand) {
        return brandService.update(brand);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Brand getById(@PathVariable int id) {
        return brandService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Brand> getAll() {
        return brandService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id ){
        brandService.delete(id);
    }
}
