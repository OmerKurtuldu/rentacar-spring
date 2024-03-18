package com.turkcell.rentacar.business.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedBrandRequest {
    @NotNull(message = "Id field cannot be bull")
    private int id;
    @NotNull(message = "Name field cannot be bull")
    private String name;
}
