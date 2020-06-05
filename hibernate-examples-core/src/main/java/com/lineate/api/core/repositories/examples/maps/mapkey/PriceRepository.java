package com.lineate.api.core.repositories.examples.maps.mapkey;

import com.lineate.api.core.domain.examples.maps.mapkey.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
