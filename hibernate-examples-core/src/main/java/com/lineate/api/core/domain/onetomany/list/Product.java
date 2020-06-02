package com.lineate.api.core.domain.onetomany.list;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    protected String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(
        name = "product_id",
        nullable = false
    )
    @OrderColumn(
        name = "price_position",
        nullable = false
    )
    private List<Price> prices = new ArrayList<>();
}
