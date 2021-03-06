package com.lineate.api.core.domain.examples.manytomany.ternary;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
@Table(name = "category_product")
@Getter
@NoArgsConstructor
public class CategorizedProduct {
    @ManyToOne
    @JoinColumn(
        name = "product_id",
        nullable = false, updatable = false
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
        name = "user_id",
        updatable = false
    )
    @NotNull
    private User addedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @NotNull
    private Date addedOn = new Date();

    public CategorizedProduct(User addedBy, Product product) {
        this.addedBy = addedBy;
        this.product = product;
    }
}
