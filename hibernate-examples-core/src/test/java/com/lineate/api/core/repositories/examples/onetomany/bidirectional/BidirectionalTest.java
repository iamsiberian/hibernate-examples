package com.lineate.api.core.repositories.examples.onetomany.bidirectional;

import com.lineate.api.core.domain.examples.onetomany.bidirectional.Price;
import com.lineate.api.core.domain.examples.onetomany.bidirectional.Product;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Collection;

import static com.lineate.api.core.domain.examples.onetomany.bidirectional.BidirectionalUtils.createPrice;
import static com.lineate.api.core.domain.examples.onetomany.bidirectional.BidirectionalUtils.createProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForBidirectional.class)
public class BidirectionalTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private Price price1;
    private Price price2;

    @BeforeEach
    public void setUp() {
        product = createProduct("product");
        testEntityManager.persist(product);

        price1 = createPrice(product, new BigDecimal("123.00"));
        product.getPrices().add(price1);
        testEntityManager.persist(price1);

        price2 = createPrice(product, new BigDecimal("456.00"));
        product.getPrices().add(price2);
        testEntityManager.persist(price2);
    }

    @Test
    public void should_find() {
        Product productLocal = productRepository.getOne(product.getId());

        assertEquals(2, productLocal.getPrices().size());
    }

    @Test
    public void should_find_prices_by_product_id() {
        Collection<Price> prices = priceRepository.getPricesByProductId(product.getId());

        assertEquals(2, prices.size());
    }
}
