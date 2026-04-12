package com.dev_ian.dscommerce.dto;

import jakarta.persistence.Column;

// Inserir categoria no futuro
public class ProductCreateRequest {
    private String name;
    private String description;
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

    public Double getPrice() { return price; }

    public String getImgUrl() { return imgUrl; }
}
