package com.oreumi.oreumi_backend.repository.product;

import com.oreumi.oreumi_backend.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
