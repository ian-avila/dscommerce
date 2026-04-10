package com.dev_ian.dscommerce.services;

import com.dev_ian.dscommerce.dto.*;
import com.dev_ian.dscommerce.entities.Product;
import com.dev_ian.dscommerce.mappers.ProductMapper;
import com.dev_ian.dscommerce.repositories.ProductRepository;
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
        Optional<Product> optProduct = repository.findById(id);
        return ProductMapper.toResponse(optProduct.get());
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
}
