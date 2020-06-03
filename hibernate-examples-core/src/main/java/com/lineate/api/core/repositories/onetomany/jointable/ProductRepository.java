package com.lineate.api.core.repositories.onetomany.jointable;

import com.lineate.api.core.domain.onetomany.jointable.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
