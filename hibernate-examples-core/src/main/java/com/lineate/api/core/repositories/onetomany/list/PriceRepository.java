package com.lineate.api.core.repositories.onetomany.list;

import com.lineate.api.core.domain.onetomany.list.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
