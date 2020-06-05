package com.lineate.api.core.domain.examples.manytomany.linkentity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "category_product")
@org.hibernate.annotations.Immutable
@Getter
@NoArgsConstructor
public class CategorizedProduct {
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "category_id")
        private Long categoryId;

        @Column(name = "product_id")
        private Long productId;
    }

    @EmbeddedId
    private Id id = new Id();

    @Column(updatable = false)
    @NotNull
    private String addedBy;

    @Column(updatable = false)
    @NotNull
    private Date addedOn = new Date();

    @ManyToOne
    @JoinColumn(
        name = "category_id",
        insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(
        name = "product_id",
        insertable = false, updatable = false)
    private Product product;

    public CategorizedProduct(
        String addedByUsername,
        Category category,
        Product product
    ) {
        this.addedBy = addedByUsername;
        this.category = category;
        this.product = product;

        this.id.categoryId = category.getId();
        this.id.productId = product.getId();

        category.getCategorizedProducts().add(this);
        product.getCategorizedProducts().add(this);
    }
}
