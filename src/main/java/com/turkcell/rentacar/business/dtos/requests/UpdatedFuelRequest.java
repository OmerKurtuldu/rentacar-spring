package com.turkcell.rentacar.business.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedFuelRequest {
    @NotNull(message = "Id field cannot be bull")
    private int id;
    @NotNull(message = "Name field cannot be bull")
    private String name;
}
