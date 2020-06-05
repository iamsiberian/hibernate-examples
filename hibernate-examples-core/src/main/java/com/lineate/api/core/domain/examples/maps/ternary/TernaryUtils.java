package com.lineate.api.core.domain.examples.maps.ternary;

public class TernaryUtils {
    public static Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    public static Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    public static User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }
}
