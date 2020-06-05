package com.lineate.api.core.repositories.examples.manytomany.linkentity;

import com.lineate.api.core.domain.examples.manytomany.linkentity.CategorizedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorizedProductRepository extends JpaRepository<CategorizedProduct, Long> {
}
