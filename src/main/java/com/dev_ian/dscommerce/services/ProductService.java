package com.dev_ian.dscommerce.services;

import com.dev_ian.dscommerce.dto.ProductDTO;
import com.dev_ian.dscommerce.entities.Product;
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
    public ProductDTO findById(Long id) {
        Optional<Product> optProduct = repository.findById(id);
        return new ProductDTO(optProduct.get());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.findAll(pageable);
       return result.map(ProductDTO::new);
    }
}
