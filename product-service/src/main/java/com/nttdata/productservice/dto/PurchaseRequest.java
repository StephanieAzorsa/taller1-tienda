package com.nttdata.productservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequest {
    @NotNull(message = "Cantidad de producto comprado requerido")
    private Integer countPurchase;
}
