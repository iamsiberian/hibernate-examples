package com.lineate.api.core.repositories.examples.onetomany.bidirectional;

import com.lineate.api.core.domain.examples.onetomany.bidirectional.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
