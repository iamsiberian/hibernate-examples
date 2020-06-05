package com.lineate.api.core.repositories.maps.mapkey;

import com.lineate.api.core.domain.maps.mapkey.Price;
import com.lineate.api.core.domain.maps.mapkey.Product;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Map;

import static com.lineate.api.core.domain.maps.mapkey.MapKeyUtils.createPrice;
import static com.lineate.api.core.domain.maps.mapkey.MapKeyUtils.createProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForMapKey.class)
public class MapKeyTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceRepository priceRepository;

    private Product product;
    private Price price1;
    private Price price2;

    @BeforeEach
    public void setUp() {
        product = createProduct("product");
        testEntityManager.persist(product);

        price1 = createPrice(product, new BigDecimal("123.00"));
        testEntityManager.persist(price1);
        product.getPrices().put(price1.getId(), price1);

        price2 = createPrice(product, new BigDecimal("456.00"));
        testEntityManager.persist(price2);
        product.getPrices().put(price2.getId(), price2);
    }

    @Test
    public void should_find_product() {
        Product productLocal = productRepository.getOne(product.getId());

        assertEquals(2, productLocal.getPrices().size());

        for (Map.Entry<Long, Price> entry : productLocal.getPrices().entrySet()) {
            assertEquals(entry.getKey(), entry.getValue().getId());
        }

    }

    @Test
    public void should_find_price() {
        Price price1local = priceRepository.getOne(price1.getId());

        assertEquals(product, price1local.getProduct());
    }
}
