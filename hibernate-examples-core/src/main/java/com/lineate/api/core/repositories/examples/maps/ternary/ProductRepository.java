package com.lineate.api.core.repositories.examples.maps.ternary;

import com.lineate.api.core.domain.examples.maps.ternary.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
