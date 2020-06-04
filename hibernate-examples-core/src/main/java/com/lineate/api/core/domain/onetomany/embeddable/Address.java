package com.lineate.api.core.domain.onetomany.embeddable;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotNull
    @Column(nullable = false)
    protected String street;

    @NotNull
    @Column(nullable = false)
    protected String city;

    @OneToMany
    @JoinColumn(
        name = "delivery_address_user_id",
        nullable = false
    )
    protected Set<Shipment> deliveries = new HashSet<>();
}
