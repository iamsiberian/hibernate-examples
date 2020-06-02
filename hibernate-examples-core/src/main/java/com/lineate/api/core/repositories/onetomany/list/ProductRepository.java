package com.lineate.api.core.repositories.onetomany.list;

import com.lineate.api.core.domain.onetomany.list.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
