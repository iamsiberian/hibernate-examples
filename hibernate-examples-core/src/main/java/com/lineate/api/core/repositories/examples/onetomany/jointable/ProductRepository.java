package com.lineate.api.core.repositories.examples.onetomany.jointable;

import com.lineate.api.core.domain.examples.onetomany.jointable.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
