package com.lineate.api.core.repositories.examples.onetoone.mapsid;

import com.lineate.api.core.domain.examples.onetoone.mapsid.User;
import com.lineate.api.core.domain.examples.onetoone.mapsid.UserProfile;
import com.lineate.api.core.domain.examples.onetoone.mapsid.UserType;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.examples.onetoone.mapsid.MapsIdEntityUtils.createUser;
import static com.lineate.api.core.domain.examples.onetoone.mapsid.MapsIdEntityUtils.createUserProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = TestConfigurationForMapsId.class)
public class UserProfileRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    private User user1;
    private User user2;
    private UserProfile user1Profile;
    private UserProfile user2Profile;

    private UserProfile user1ProfileAfterSave;

    @BeforeEach
    public void setUp() {
        user1 = entityManager.persist(createUser(
            "johndoe@gmail.com", "hashpass", "John Doe", UserType.USER
        ));
        user2 = entityManager.persist(createUser(
            "sararay@gmail.com", "abrakadabra", "Sara Ray", UserType.ADMIN
        ));
        user1Profile = createUserProfile(user1.getName(), user1);
        user2Profile = createUserProfile(user2.getName(), user2);

        user1ProfileAfterSave = entityManager.persist(user1Profile);
    }

    @Test
    public void should_store_a_user_profile() {
        UserProfile user2ProfileAfterSave = userProfileRepository.save(user2Profile);
        assertNotNull(user2ProfileAfterSave.getId());
        assertEquals(user2ProfileAfterSave.getId(), user2.getId());
    }

    @Test
    public void eager_user_in_a_user_profile() {
        UserProfile userProfile1 = userProfileRepository.getOne(user1ProfileAfterSave.getId());
        assertTrue(Hibernate.isInitialized(userProfile1.getUser()));
    }

    @Test
    public void one_to_one_is_unidirectional() {
        User user1local = userRepository.getOne(user1.getId());
        assertNull(user1local.getUserProfile());
    }
}
