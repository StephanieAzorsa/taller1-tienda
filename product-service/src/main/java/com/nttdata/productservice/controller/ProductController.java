package com.nttdata.productservice.controller;

import com.nttdata.productservice.dto.ProductRequestDTO;
import com.nttdata.productservice.dto.ProductResponseDTO;
import com.nttdata.productservice.dto.ProductUpdateRequestDTO;
import com.nttdata.productservice.dto.QuantityProductRequestDTO;
import com.nttdata.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}/add-stock")
    public ResponseEntity<ProductResponseDTO> addStock(
            @PathVariable String id,
            @Validated @RequestBody QuantityProductRequestDTO addStockRequestDTO) {

        ProductResponseDTO productResponseDTO = productService.addStock(id, addStockRequestDTO);
        return ResponseEntity.ok(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Validated()
            @RequestBody ProductRequestDTO productRequestDTO) {

        ProductResponseDTO productResponseDTO = productService
                .createProduct(productRequestDTO);
        return ResponseEntity.ok().body(productResponseDTO);
    }

    @PutMapping("/{id}/purchase")
    public ResponseEntity<ProductResponseDTO> purchaseProduct(
            @PathVariable String id,
            @Validated @RequestBody QuantityProductRequestDTO purchaseRequestDTO) {

        ProductResponseDTO productResponseDTO = productService
                .purchaseProduct(id, purchaseRequestDTO);

        return ResponseEntity.ok(productResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable String id,
            @Validated @RequestBody ProductUpdateRequestDTO productRequestDTO) {

        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok(updatedProduct);
    }
}
