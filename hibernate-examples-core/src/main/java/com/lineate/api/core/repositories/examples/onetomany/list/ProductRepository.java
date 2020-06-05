package com.lineate.api.core.repositories.examples.onetomany.list;

import com.lineate.api.core.domain.examples.onetomany.list.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
