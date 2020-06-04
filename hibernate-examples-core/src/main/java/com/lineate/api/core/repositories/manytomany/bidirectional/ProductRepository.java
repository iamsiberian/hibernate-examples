package com.lineate.api.core.repositories.manytomany.bidirectional;

import com.lineate.api.core.domain.manytomany.bidirectional.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
