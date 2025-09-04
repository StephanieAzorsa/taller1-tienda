package com.nttdata.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantityProductRequestDTO {
    @NotNull(message = "Cantidad de producto comprado requerido")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;
}
