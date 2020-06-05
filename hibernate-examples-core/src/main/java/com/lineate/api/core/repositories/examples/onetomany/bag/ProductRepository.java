package com.lineate.api.core.repositories.examples.onetomany.bag;

import com.lineate.api.core.domain.examples.onetomany.bag.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
