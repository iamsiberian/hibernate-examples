package com.lineate.api.core.repositories.onetomany.bag;

import com.lineate.api.core.domain.onetomany.bag.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
