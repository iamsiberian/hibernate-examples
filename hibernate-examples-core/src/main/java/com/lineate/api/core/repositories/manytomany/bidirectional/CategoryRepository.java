package com.lineate.api.core.repositories.manytomany.bidirectional;

import com.lineate.api.core.domain.manytomany.bidirectional.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
