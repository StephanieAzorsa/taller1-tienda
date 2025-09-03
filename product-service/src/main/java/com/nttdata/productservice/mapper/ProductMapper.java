package com.nttdata.productservice.mapper;

import com.nttdata.productservice.dto.ProductRequestDTO;
import com.nttdata.productservice.dto.ProductResponseDTO;
import com.nttdata.productservice.model.Product;

public class ProductMapper {

    public static ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO productDTO = new ProductResponseDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());

        return  productDTO;
    }

    public static Product toModel(ProductRequestDTO productRequestDTO) {
        Product product = new Product();

        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());

        return product;
    }
}
