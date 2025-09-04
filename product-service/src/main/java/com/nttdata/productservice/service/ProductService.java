package com.nttdata.productservice.service;

import com.nttdata.productservice.Exception.InsufficientStockException;
import com.nttdata.productservice.Exception.ProductNotFoundException;
import com.nttdata.productservice.dto.ProductRequestDTO;
import com.nttdata.productservice.dto.ProductResponseDTO;
import com.nttdata.productservice.dto.QuantityProductRequestDTO;
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
                .map(product -> ProductMapper.toDTO(product))
                .toList();
    }

    public ProductResponseDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + id));
        return ProductMapper.toDTO(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product newProduct = productRepository
                .save(ProductMapper.toModel(productRequestDTO));

        return ProductMapper.toDTO(newProduct);
    }

    public ProductResponseDTO addStock(String id,
                                       QuantityProductRequestDTO quantityProductRequestDTO) {

        int quantity = quantityProductRequestDTO.getQuantity();

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + id));

        product.setStock(product.getStock() + quantity);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }

    public ProductResponseDTO purchaseProduct(String id,
                                              QuantityProductRequestDTO quantityProductRequestDTO) {

        int quantity = quantityProductRequestDTO.getQuantity();

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado con ID: " + id));

        if (product.getStock() < quantity) {
            throw new InsufficientStockException(
                    "Stock insuficiente. Stock disponible: " + product.getStock() +
                            ", cantidad solicitada: " + quantity
            );
        }

        product.setStock(product.getStock() - quantity);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id);
    }

}
