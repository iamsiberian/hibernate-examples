package com.lineate.api.core.repositories.maps.mapkey;

import com.lineate.api.core.domain.maps.mapkey.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
