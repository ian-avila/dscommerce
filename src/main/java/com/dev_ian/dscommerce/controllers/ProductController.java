package com.dev_ian.dscommerce.controllers;

import com.dev_ian.dscommerce.dto.ProductDTO;
import com.dev_ian.dscommerce.entities.Product;
import com.dev_ian.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public Page<ProductDTO> findAll(Pageable pageable) {
       return productService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public Product insert(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }
}
