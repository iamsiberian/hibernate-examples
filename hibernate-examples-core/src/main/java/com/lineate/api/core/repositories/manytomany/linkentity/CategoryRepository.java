package com.lineate.api.core.repositories.manytomany.linkentity;

import com.lineate.api.core.domain.manytomany.linkentity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
