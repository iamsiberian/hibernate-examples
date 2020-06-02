package com.lineate.api.core.repositories.onetomany.bag;

import com.lineate.api.core.domain.onetomany.bag.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
