package com.lineate.api.core.domain.manytomany.ternary;

public class TernaryUtils {
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

    public static CategorizedProduct createLinkEntity(User user, Product product) {
        return new CategorizedProduct(user, product);
    }

    public static User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }
}
