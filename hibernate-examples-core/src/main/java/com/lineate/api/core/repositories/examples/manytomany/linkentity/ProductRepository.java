package com.lineate.api.core.repositories.examples.manytomany.linkentity;

import com.lineate.api.core.domain.examples.manytomany.linkentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
