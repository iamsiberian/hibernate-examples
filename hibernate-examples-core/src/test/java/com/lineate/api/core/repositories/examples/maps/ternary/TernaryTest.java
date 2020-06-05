package com.lineate.api.core.repositories.examples.maps.ternary;

import com.lineate.api.core.domain.examples.maps.ternary.Category;
import com.lineate.api.core.domain.examples.maps.ternary.Product;
import com.lineate.api.core.domain.examples.maps.ternary.User;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.examples.maps.ternary.TernaryUtils.createCategory;
import static com.lineate.api.core.domain.examples.maps.ternary.TernaryUtils.createProduct;
import static com.lineate.api.core.domain.examples.maps.ternary.TernaryUtils.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = TestConfigurationForTernary.class)
public class TernaryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    private Category category1;
    private Category category2;
    private Product product1;
    private Product product2;
    private User user;

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

        user = createUser("johndoe");
        testEntityManager.persist(user);

        category1.getProductAddedBy().put(product1, user);
        category1.getProductAddedBy().put(product2, user);
        category2.getProductAddedBy().put(product1, user);
    }

    @Test
    public void should_find_product() {
        Category category1local = categoryRepository.getOne(category1.getId());
        Category category2local = categoryRepository.getOne(category2.getId());

        Product product1local = productRepository.getOne(product1.getId());
        User userLocal = userRepository.getOne(user.getId());

        assertEquals(2, category1local.getProductAddedBy().size());
        assertEquals(1, category2local.getProductAddedBy().size());
        assertEquals(product1local, category2local.getProductAddedBy().keySet().iterator().next());
        assertEquals(userLocal, category2local.getProductAddedBy().values().iterator().next());
    }
}
