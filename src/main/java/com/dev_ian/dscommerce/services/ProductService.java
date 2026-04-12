package com.dev_ian.dscommerce.services;

import com.dev_ian.dscommerce.dto.ProductCreateRequest;
import com.dev_ian.dscommerce.dto.ProductResponse;
import com.dev_ian.dscommerce.dto.ProductSummary;
import com.dev_ian.dscommerce.dto.ProductUpdateRequest;
import com.dev_ian.dscommerce.entities.Product;
import com.dev_ian.dscommerce.mappers.ProductMapper;
import com.dev_ian.dscommerce.repositories.ProductRepository;
import com.dev_ian.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return ProductMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductSummary> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
       return result.map(ProductMapper::toSummary);
    }

    @Transactional
    public ProductResponse insert(ProductCreateRequest productCreateRequest) {
        Product product = repository.save(ProductMapper.toEntity(productCreateRequest));
        return ProductMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest productUpdateRequest) {
        Optional<Product> opt = repository.findById(id);
        ProductMapper.updateEntity(productUpdateRequest, opt.get());
        return ProductMapper.toResponse(opt.get());
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
