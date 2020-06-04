package com.lineate.api.core.domain.manytomany.bidirectional;

public class BidirectionalUtils {
    public static Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    public static Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
