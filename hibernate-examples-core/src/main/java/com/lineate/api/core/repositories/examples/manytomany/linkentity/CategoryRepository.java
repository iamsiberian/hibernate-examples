package com.lineate.api.core.repositories.examples.manytomany.linkentity;

import com.lineate.api.core.domain.examples.manytomany.linkentity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
