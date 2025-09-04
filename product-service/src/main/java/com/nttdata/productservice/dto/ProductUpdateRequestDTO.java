package com.nttdata.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequestDTO {
    @NotBlank(message = "Nombre del producto requerido")
    @Size(max = 100, message = "Nombre no puede exceder 100 caracteres")
    private String name;

    @NotNull(message = "Precio del producto requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;
}

