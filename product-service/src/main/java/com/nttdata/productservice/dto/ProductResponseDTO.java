package com.nttdata.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private String id;
    private String name;
    private Double price;
    private Integer stock;
}
