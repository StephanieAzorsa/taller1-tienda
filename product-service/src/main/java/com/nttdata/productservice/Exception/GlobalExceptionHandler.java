package com.nttdata.productservice.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {

        log.warn("El producto no se encuentra con ID {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Producto no encontrado");

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, String>> handleInsufficientStockException(InsufficientStockException ex) {
        log.warn("No hay suficiente stock: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Stock insuficiente");

        return ResponseEntity.badRequest().body(errors);
    }
}