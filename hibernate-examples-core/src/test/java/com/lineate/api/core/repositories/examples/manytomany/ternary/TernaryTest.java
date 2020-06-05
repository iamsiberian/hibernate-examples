package com.lineate.api.core.repositories.examples.manytomany.ternary;

import com.lineate.api.core.domain.examples.manytomany.ternary.CategorizedProduct;
import com.lineate.api.core.domain.examples.manytomany.ternary.Category;
import com.lineate.api.core.domain.examples.manytomany.ternary.Product;
import com.lineate.api.core.domain.examples.manytomany.ternary.User;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.lineate.api.core.domain.examples.manytomany.ternary.TernaryUtils.createCategory;
import static com.lineate.api.core.domain.examples.manytomany.ternary.TernaryUtils.createLinkEntity;
import static com.lineate.api.core.domain.examples.manytomany.ternary.TernaryUtils.createProduct;
import static com.lineate.api.core.domain.examples.manytomany.ternary.TernaryUtils.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForTernary.class)
public class TernaryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    private User user;
    private Product product1;
    private Product product2;
    private Category category1;
    private Category category2;
    private CategorizedProduct linkEntity1;
    private CategorizedProduct linkEntity2;
    private CategorizedProduct linkEntity3;

    @BeforeEach
    public void setUp() {
        user = createUser("johnDoe");
        testEntityManager.persist(user);

        category1 = createCategory("category1");
        category2 = createCategory("category2");
        testEntityManager.persist(category1);
        testEntityManager.persist(category2);

        product1 = createProduct("product1");
        product2 = createProduct("product2");
        testEntityManager.persist(product1);
        testEntityManager.persist(product2);

        linkEntity1 = createLinkEntity(user, product1);
        linkEntity2 = createLinkEntity(user, product2);
        linkEntity3 = createLinkEntity(user, product1);
        category1.getCategorizedProducts().add(linkEntity1);
        category1.getCategorizedProducts().add(linkEntity2);
        category2.getCategorizedProducts().add(linkEntity3);
    }

    @Test
    public void should_find() {
        Category category1local = categoryRepository.getOne(category1.getId());
        Category category2local = categoryRepository.getOne(category2.getId());

        Product product1local = productRepository.getOne(product1.getId());

        User userlocal = userRepository.getOne(user.getId());
        assertEquals(2, category1local.getCategorizedProducts().size());

        assertEquals(1, category2local.getCategorizedProducts().size());

        assertEquals(product1local, category2local.getCategorizedProducts().iterator().next().getProduct());
        assertEquals(userlocal, category2local.getCategorizedProducts().iterator().next().getAddedBy());
    }

    @Test
    public void should_find_categories_by_product () {
        Product product1local = productRepository.getOne(product1.getId());
        List<Category> categories = categoryRepository.getCategoriesByProducts(product1local);

        assertEquals(2, categories.size());
    }
}
