package com.dev_ian.dscommerce.controllers;

import com.dev_ian.dscommerce.dto.*;
import com.dev_ian.dscommerce.entities.Product;
import com.dev_ian.dscommerce.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<ProductResponse> insert(@RequestBody ProductCreateRequest productCreateRequest) {
        ProductResponse dto = productService.insert(productCreateRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductUpdateRequest productUpdateRequest, @PathVariable Long id) {
        return ResponseEntity.ok(productService.update(id, productUpdateRequest));
    }
}
