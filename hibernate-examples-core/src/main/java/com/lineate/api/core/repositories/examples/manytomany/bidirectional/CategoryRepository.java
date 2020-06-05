package com.lineate.api.core.repositories.examples.manytomany.bidirectional;

import com.lineate.api.core.domain.examples.manytomany.bidirectional.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
