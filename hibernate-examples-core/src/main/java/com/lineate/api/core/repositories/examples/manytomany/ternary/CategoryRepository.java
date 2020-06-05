package com.lineate.api.core.repositories.examples.manytomany.ternary;

import com.lineate.api.core.domain.examples.manytomany.ternary.Category;
import com.lineate.api.core.domain.examples.manytomany.ternary.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(
        "select c " +
            "from Category c " +
            "join c.categorizedProducts cp " +
            "where cp.product = :product"
    )
    List<Category> getCategoriesByProducts(@Param("product") Product product);
}
