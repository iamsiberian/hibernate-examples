package com.lineate.api.core.domain.examples.manytomany.linkentity;

public class LinkEntityUtils {
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

    public static CategorizedProduct createLinkEntity(String addedByUsername, Category category, Product product) {
        return new CategorizedProduct(addedByUsername, category, product);
    }
}
