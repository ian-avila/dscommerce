package com.dev_ian.dscommerce.repositories;

import com.dev_ian.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
