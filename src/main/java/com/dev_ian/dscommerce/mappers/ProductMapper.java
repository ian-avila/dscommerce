package com.dev_ian.dscommerce.mappers;

import com.dev_ian.dscommerce.dto.ProductCreateRequest;
import com.dev_ian.dscommerce.dto.ProductResponse;
import com.dev_ian.dscommerce.dto.ProductSummary;
import com.dev_ian.dscommerce.dto.ProductUpdateRequest;
import com.dev_ian.dscommerce.entities.Product;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

public final class ProductMapper {

    private ProductMapper() {
    }

    /**
     * DTO -> Entity (Create)
     */
    public static Product toEntity(ProductCreateRequest productCreateRequest) {

        return new Product( null,
                productCreateRequest.getName(),
                productCreateRequest.getDescription(),
                productCreateRequest.getPrice(),
                productCreateRequest.getImgUrl());
    }

    /**
     * Entity -> DTO
     */
    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl());
    }

    /**
     * Entity -> Summary
     */
    public static ProductSummary toSummary(Product product) {
        return new ProductSummary(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl());
    }

    public static void updateEntity(ProductUpdateRequest productUpdateRequest, Product product) {
        product.setName(productUpdateRequest.getName());
        product.setDescription(productUpdateRequest.getDescription());
        product.setPrice(productUpdateRequest.getPrice());
        product.setImgUrl(productUpdateRequest.getImgUrl());
    }
}

