package com.lineate.api.core.repositories.examples.manytomany.bidirectional;

import com.lineate.api.core.domain.examples.manytomany.bidirectional.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
