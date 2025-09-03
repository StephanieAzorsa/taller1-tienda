package com.nttdata.productservice.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "products")
public class Product {
    @BsonId
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;
}
