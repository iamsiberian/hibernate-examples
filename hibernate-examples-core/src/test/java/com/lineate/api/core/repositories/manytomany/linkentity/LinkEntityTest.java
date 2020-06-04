package com.lineate.api.core.repositories.manytomany.linkentity;

import com.lineate.api.core.domain.manytomany.linkentity.CategorizedProduct;
import com.lineate.api.core.domain.manytomany.linkentity.Category;
import com.lineate.api.core.domain.manytomany.linkentity.Product;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.manytomany.linkentity.LinkEntityUtils.createCategory;
import static com.lineate.api.core.domain.manytomany.linkentity.LinkEntityUtils.createLinkEntity;
import static com.lineate.api.core.domain.manytomany.linkentity.LinkEntityUtils.createProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForLinkEntity.class)
public class LinkEntityTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Category category1;
    private Category category2;
    private CategorizedProduct linkEntity1;
    private CategorizedProduct linkEntity2;
    private CategorizedProduct linkEntity3;

    @BeforeEach
    public void setUp() {
        category1 = createCategory("category1");
        category2 = createCategory("category2");
        testEntityManager.persist(category1);
        testEntityManager.persist(category2);

        product1 = createProduct("product1");
        product2 = createProduct("product2");
        testEntityManager.persist(product1);
        testEntityManager.persist(product2);

        linkEntity1 = createLinkEntity("johnDoe", category1, product1);
        linkEntity2 = createLinkEntity("johnDoe", category1, product2);
        linkEntity3 = createLinkEntity("johnDoe", category2, product1);
        testEntityManager.persist(linkEntity1);
        testEntityManager.persist(linkEntity2);
        testEntityManager.persist(linkEntity3);
    }

    @Test
    public void should_find() {
        Category category1local = categoryRepository.getOne(category1.getId());
        Category category2local = categoryRepository.getOne(category2.getId());

        Product product1local = productRepository.getOne(product1.getId());
        Product product2local = productRepository.getOne(product2.getId());

        assertEquals(2, category1local.getCategorizedProducts().size());
        assertEquals(2, product1local.getCategorizedProducts().size());

        assertEquals(1, category2local.getCategorizedProducts().size());
        assertEquals(1, product2local.getCategorizedProducts().size());

        assertEquals(product1local, category2local.getCategorizedProducts().iterator().next().getProduct());
        assertEquals(category1local, product2local.getCategorizedProducts().iterator().next().getCategory());
    }
}
