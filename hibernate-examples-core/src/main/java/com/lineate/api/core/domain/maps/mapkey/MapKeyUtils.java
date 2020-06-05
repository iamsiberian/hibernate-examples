package com.lineate.api.core.domain.maps.mapkey;

import java.math.BigDecimal;

public class MapKeyUtils {
    public static Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    public static Price createPrice(Product product, BigDecimal amount) {
        Price price = new Price();
        price.setProduct(product);
        price.setAmount(amount);
        return price;
    }
}
