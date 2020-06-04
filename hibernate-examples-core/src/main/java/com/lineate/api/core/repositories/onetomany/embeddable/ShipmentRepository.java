package com.lineate.api.core.repositories.onetomany.embeddable;

import com.lineate.api.core.domain.onetomany.embeddable.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
