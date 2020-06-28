package com.lineate.api.core.repositories.examples.onetoone.mapsid;

import com.lineate.api.core.domain.examples.onetoone.mapsid.Post;
import com.lineate.api.core.domain.examples.onetoone.mapsid.PostDetails;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.examples.onetoone.mapsid.MapsIdEntityUtils.createPost;
import static com.lineate.api.core.domain.examples.onetoone.mapsid.MapsIdEntityUtils.createPostDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = TestConfigurationForMapsId.class)
public class PostDetailsRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostDetailsRepository postDetailsRepository;

    private Post post1;
    private Post post2;
    private PostDetails postDetails1;
    private PostDetails postDetails2;

    private PostDetails user1ProfileAfterSave;

    @BeforeEach
    public void setUp() {
        post1 = entityManager.persist(createPost(
            "post1"
        ));
        post2 = entityManager.persist(createPost(
            "post2"
        ));
        postDetails1 = createPostDetails(post1.getText(), post1);
        postDetails2 = createPostDetails(post2.getText(), post2);

        user1ProfileAfterSave = entityManager.persist(postDetails1);
    }

    @Test
    public void should_store_a_post_details() {
        PostDetails user2ProfileAfterSave = postDetailsRepository.save(postDetails2);
        assertNotNull(user2ProfileAfterSave.getId());
        assertEquals(user2ProfileAfterSave.getId(), post2.getId());
    }

    @Test
    public void eager_user_in_a_post_details() {
        PostDetails postDetails1 = postDetailsRepository.getOne(user1ProfileAfterSave.getId());
        assertTrue(Hibernate.isInitialized(postDetails1.getPost()));
    }

    @Test
    public void one_to_one_is_unidirectional() {
        Post post1Local = postRepository.getOne(post1.getId());
        assertNull(post1Local.getPostDetails());
    }
}
