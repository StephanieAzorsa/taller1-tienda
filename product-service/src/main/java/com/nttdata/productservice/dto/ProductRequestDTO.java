package com.nttdata.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {
    @NotBlank(message = "Nombre del producto requerido")
    @Size(max = 100, message = "Nombre no puede exceder 100 caracteres")
    private String name;

    @NotNull(message = "Precio del producto requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Stock del producto requerido")
    private Integer stock;

}
