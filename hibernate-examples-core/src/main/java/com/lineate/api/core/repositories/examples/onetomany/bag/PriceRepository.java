package com.lineate.api.core.repositories.examples.onetomany.bag;

import com.lineate.api.core.domain.examples.onetomany.bag.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
