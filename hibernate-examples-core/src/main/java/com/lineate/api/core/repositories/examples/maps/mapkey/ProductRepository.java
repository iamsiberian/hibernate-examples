package com.lineate.api.core.repositories.examples.maps.mapkey;

import com.lineate.api.core.domain.examples.maps.mapkey.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
