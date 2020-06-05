package com.lineate.api.core.repositories.examples.onetomany.embeddable;

import com.lineate.api.core.domain.examples.onetomany.embeddable.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
