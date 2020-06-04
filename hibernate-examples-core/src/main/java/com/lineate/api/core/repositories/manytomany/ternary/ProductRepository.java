package com.lineate.api.core.repositories.manytomany.ternary;

import com.lineate.api.core.domain.manytomany.ternary.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
