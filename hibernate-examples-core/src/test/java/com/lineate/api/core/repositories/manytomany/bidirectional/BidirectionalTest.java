package com.lineate.api.core.repositories.manytomany.bidirectional;

import com.lineate.api.core.domain.manytomany.bidirectional.Category;
import com.lineate.api.core.domain.manytomany.bidirectional.Product;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.manytomany.bidirectional.BidirectionalUtils.createCategory;
import static com.lineate.api.core.domain.manytomany.bidirectional.BidirectionalUtils.createProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForBidirectional.class)
public class BidirectionalTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    private Category category1;
    private Category category2;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        category1 = createCategory("category1");
        category2 = createCategory("category2");

        product1 = createProduct("product1");
        product2 = createProduct("product2");

        category1.getProducts().add(product1);
        product1.getCategories().add(category1);

        category1.getProducts().add(product2);
        product2.getCategories().add(category1);

        category2.getProducts().add(product1);
        product1.getCategories().add(category2);

        testEntityManager.persist(category1);
        testEntityManager.persist(category2);
    }

    @Test
    public void should_find() {
        Category category1local = categoryRepository.getOne(category1.getId());
        Category category2local = categoryRepository.getOne(category2.getId());

        Product product1local = productRepository.getOne(product1.getId());
        Product product2local = productRepository.getOne(product2.getId());

        assertEquals(2, category1local.getProducts().size());
        assertEquals(2, product1local.getCategories().size());

        assertEquals(1, category2local.getProducts().size());
        assertEquals(1, product2local.getCategories().size());

        assertEquals(product1local, category2local.getProducts().iterator().next());
        assertEquals(category1local, product2local.getCategories().iterator().next());
    }
}
