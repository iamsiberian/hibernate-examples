package com.lineate.api.core.domain.examples.onetomany.jointable;

public class JoinTableUtils {
    public static Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    public static User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
