package com.lineate.api.core.repositories.manytomany.linkentity;

import com.lineate.api.core.domain.manytomany.linkentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
