package com.nttdata.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDTO {
    private String id;
    private String name;
    private Double price;
    private Integer stock;
}
