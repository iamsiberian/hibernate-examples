package com.lineate.api.core.repositories.onetomany.jointable;

import com.lineate.api.core.domain.onetomany.jointable.Product;
import com.lineate.api.core.domain.onetomany.jointable.User;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.onetomany.jointable.JoinTableUtils.createProduct;
import static com.lineate.api.core.domain.onetomany.jointable.JoinTableUtils.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = TestConfigurationForJoinTable.class)
public class JoinTableTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Product unsoldProduct;
    private User user;

    @BeforeEach
    public void setUp() {
        product1 = createProduct("product1");
        testEntityManager.persist(product1);
        product2 = createProduct("product2");
        testEntityManager.persist(product2);

        user = createUser("user1");
        user.getBoughtProducts().add(product1);
        product1.setBuyer(user);
        user.getBoughtProducts().add(product2);
        product2.setBuyer(user);
        testEntityManager.persist(user);

        unsoldProduct = createProduct("unsold");
        testEntityManager.persist(unsoldProduct);
    }

    @Test
    public void test_find_buyer() {
        /*
        next lombok annotations on Product#buyer
        @ToString.Exclude
        @EqualsAndHashCode.Exclude

        protect recursive dependencies from stackoverflowerror
         */
        Product product1local = productRepository.getOne(product1.getId());
        assertEquals("user1", product1local.getBuyer().getUsername());
        assertEquals(2, product1local.getBuyer().getBoughtProducts().size());
        assertTrue(product1local.getBuyer().getBoughtProducts().contains(product1local));
        assertTrue(product1local.getBuyer().getBoughtProducts().contains(product2));

        Product unsold2local = productRepository.getOne(unsoldProduct.getId());
        assertNull(unsold2local.getBuyer());
    }
}
