package com.lineate.api.core.repositories.examples.onetomany.list;

import com.lineate.api.core.domain.examples.onetomany.list.Price;
import com.lineate.api.core.domain.examples.onetomany.list.Product;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;

import static com.lineate.api.core.domain.examples.onetomany.list.ListUtils.createPrice;
import static com.lineate.api.core.domain.examples.onetomany.list.ListUtils.createProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForList.class)
public class ListTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Price price1;
    private Price price2;

    @BeforeEach
    public void setUp() {
        product1 = createProduct("product1");
        entityManager.persist(product1);

        price1 = createPrice(product1, new BigDecimal("123.00"));
        product1.getPrices().add(price1);
        //product1.getPrices().add(price1); // No persistent effect
        entityManager.persist(price1);

        price2 = createPrice(product1, new BigDecimal("456.00"));
        product1.getPrices().add(price2);
        entityManager.persist(price2);
    }

    @Test
    public void should_store_with_2_prices() {
        Product product1local = productRepository.getOne(product1.getId());
        List<Price> prices = product1local.getPrices();
        assertEquals(2, product1local.getPrices().size());

        assertEquals(prices.get(0).getBid().compareTo(new BigDecimal("123")), 0);
        assertEquals(prices.get(1).getBid().compareTo(new BigDecimal("456")), 0);
    }
}

