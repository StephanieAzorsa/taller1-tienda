package com.nttdata.productservice.service;

import com.nttdata.productservice.dto.ProductRequestDTO;
import com.nttdata.productservice.dto.ProductResponseDTO;
import com.nttdata.productservice.mapper.ProductMapper;
import com.nttdata.productservice.model.Product;
import com.nttdata.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product newProduct = productRepository
                .save(ProductMapper.toModel(productRequestDTO));

        return ProductMapper.toDTO(newProduct);
    }


}
