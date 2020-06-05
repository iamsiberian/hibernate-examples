package com.lineate.api.core.repositories.maps.ternary;

import com.lineate.api.core.domain.maps.ternary.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
