package com.lineate.api.core.repositories.examples.manytomany.ternary;

import com.lineate.api.core.domain.examples.manytomany.ternary.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
