package com.lineate.api.core.repositories.examples.maps.ternary;

import com.lineate.api.core.domain.examples.maps.ternary.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
