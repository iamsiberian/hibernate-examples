package com.lineate.api.core.domain.examples.onetomany.bidirectional;

import java.math.BigDecimal;

public class BidirectionalUtils {
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
