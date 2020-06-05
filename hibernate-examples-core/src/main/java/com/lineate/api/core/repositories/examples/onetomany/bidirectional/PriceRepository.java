package com.lineate.api.core.repositories.examples.onetomany.bidirectional;

import com.lineate.api.core.domain.examples.onetomany.bidirectional.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query(
        "select p " +
            "from Price p " +
            "where p.product.id = :productId"
    )
    Collection<Price> getPricesByProductId(@Param("productId") Long productId);
}
