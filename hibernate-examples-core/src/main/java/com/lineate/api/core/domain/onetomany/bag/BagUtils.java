package com.lineate.api.core.domain.onetomany.bag;

import java.math.BigDecimal;

public class BagUtils {
    public static Price createPrice(Product product, BigDecimal bid) {
        Price price = new Price();
        price.setProduct(product);
        price.setBid(bid);
        return price;
    }

    public static Product createProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}
