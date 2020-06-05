package com.lineate.api.core.repositories.examples.onetoone.mapsid;

import com.lineate.api.core.domain.examples.onetoone.mapsid.User;
import com.lineate.api.core.domain.examples.onetoone.mapsid.UserType;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static com.lineate.api.core.domain.examples.onetoone.mapsid.MapsIdEntityUtils.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ContextConfiguration(classes = TestConfigurationForMapsId.class)
public class UserRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = createUser("johndoe@gmail.com", "hashpass", "John Doe", UserType.USER);
        user2 = createUser("sararay@gmail.com", "abrakadabra", "Sara Ray", UserType.ADMIN);
    }

    private void assertUser(User actual, User expected) {
        assertThat(actual).hasFieldOrProperty("id");
        assertThat(actual).hasFieldOrPropertyWithValue("email", expected.getEmail());
        assertThat(actual).hasFieldOrPropertyWithValue("password", expected.getPassword());
        assertThat(actual).hasFieldOrPropertyWithValue("name", expected.getName());
        assertThat(actual).hasFieldOrPropertyWithValue("userType", expected.getUserType());
    }

    @Test
    public void should_find_no_users_if_repository_is_empty() {
        List<User> users = repository.findAll();

        assertThat(users).isEmpty();
    }

    @Test
    public void should_store_a_user() {
        User userAfterSave = repository.save(user1);

        assertUser(userAfterSave, user1);
    }

    @Test
    public void should_delete_all_user() {
        entityManager.persist(user1);
        entityManager.persist(user2);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void should_find_all_users() {
        entityManager.persist(user1);
        entityManager.persist(user2);

        Iterable<User> users = repository.findAll();

        assertThat(users).hasSize(2).contains(user1, user2);
    }

    @Test
    public void should_find_user_by_id() {
        entityManager.persist(user1);
        entityManager.persist(user2);

        Optional<User> foundCustomer = repository.findById(user2.getId());

        if (foundCustomer.isPresent()) {
            assertThat(foundCustomer.get()).isEqualTo(user2);
        } else {
            fail("User2 not found");
        }
    }
}
