package com.dev_ian.dscommerce.controllers;

import com.dev_ian.dscommerce.dto.ProductCreateRequest;
import com.dev_ian.dscommerce.dto.ProductResponse;
import com.dev_ian.dscommerce.dto.ProductSummary;
import com.dev_ian.dscommerce.dto.ProductUpdateRequest;
import com.dev_ian.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<ProductSummary>> findAll(Pageable pageable) {
       return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }


    @PostMapping
    public ResponseEntity<ProductResponse> insert(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        ProductResponse dto = productService.insert(productCreateRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductUpdateRequest productUpdateRequest, @PathVariable Long id) {
        return ResponseEntity.ok(productService.update(id, productUpdateRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
