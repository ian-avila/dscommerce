package com.dev_ian.dscommerce.dto;


import jakarta.validation.constraints.*;

// Inserir categoria no futuro
public class ProductCreateRequest {

    @Size(min = 8, max = 80,
            message = "invalid name")
    @NotBlank(message = "required field")
    private String name;

    @Size(min = 10)
    @NotBlank(message = "required field")
    private String description;

    @NotNull(message = "required field")
    @Positive(message = "value must be positive")
    private Double price;

    private String imgUrl;


    public ProductCreateRequest(String name, String description, Double price, String imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
