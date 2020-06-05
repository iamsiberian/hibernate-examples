package com.lineate.api.core.repositories.examples.onetomany.bag;

import com.lineate.api.core.domain.examples.onetomany.bag.Price;
import com.lineate.api.core.domain.examples.onetomany.bag.Product;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;


import java.math.BigDecimal;

import static com.lineate.api.core.domain.examples.onetomany.bag.BagUtils.createPrice;
import static com.lineate.api.core.domain.examples.onetomany.bag.BagUtils.createProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForBag.class)
public class BagTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Price price1;

    @BeforeEach
    public void setUp() {
        product1 = createProduct("product1");
        entityManager.persist(product1);
        price1 = createPrice(product1, new BigDecimal("123.00"));
        product1.getPrices().add(price1);
        product1.getPrices().add(price1); // No persistent effect
        entityManager.persistAndFlush(price1);
    }

    @Test
    public void should_store_with_2_same_prices() {
        assertEquals(product1.getPrices().size(), 2);
    }

    @Test
    public void should_add_price() {
        Product product = productRepository.getOne(product1.getId());

        Price price = createPrice(product, new BigDecimal("456.00"));
        /*
        if the prices field in the Product entity will has next lombok annotations
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        then hibernate will not do SELECT on prices

        also there will be no stack overflow error due to recursive dependency
         */
        product.getPrices().add(price);
        priceRepository.save(price);
    }

    @Test
    public void should_find_one_price() {
        Product product1local = productRepository.getOne(product1.getId());
        //todo: same transaction/entityManager? assertEquals(1, product1local.getPrices().size());
        assertEquals(2, product1local.getPrices().size());
    }
}

