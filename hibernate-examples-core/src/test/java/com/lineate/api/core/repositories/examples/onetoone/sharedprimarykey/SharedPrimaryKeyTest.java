package com.lineate.api.core.repositories.examples.onetoone.sharedprimarykey;

import com.lineate.api.core.domain.examples.onetoone.sharedprimarykey.Post;
import com.lineate.api.core.domain.examples.onetoone.sharedprimarykey.PostDetails;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.examples.onetoone.sharedprimarykey.SharedPrimaryKeyEntityUtils.createPost;
import static com.lineate.api.core.domain.examples.onetoone.sharedprimarykey.SharedPrimaryKeyEntityUtils.createPostDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = TestConfigurationForSharedPrimaryKey.class)
public class SharedPrimaryKeyTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostDetailsRepository postDetailsRepository;

    private Post post1;
    private PostDetails postDetails1;

    private Post post2;
    private PostDetails postDetails2;

    @BeforeEach
    public void setUp() {
        post1 = createPost("post1");
        postDetails1 = createPostDetails("postDetails1");
        entityManager.persist(postDetails1);

        post2 = createPost("post2");
        postDetails2 = createPostDetails("postDetails2");
        entityManager.persist(postDetails2);
        post2.setId(postDetails2.getId());
        post2.setPostDetails(postDetails2);
        entityManager.persist(post2);
    }

    @Test
    public void should_store_a_post_with_empty_post_details() {
        post1.setId(postDetails1.getId());

        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            postRepository.save(post1);
        });
    }

    @Test
    public void should_store_a_post_details() {
        post1.setId(postDetails1.getId());
        post1.setPostDetails(postDetails1);

        //todo postRepository.save(post1);
        entityManager.persist(post1);
    }

    @Test
    public void should_find_post() {
        Post post = postRepository.getOne(post2.getId());
        PostDetails postDetails = postDetailsRepository.getOne(postDetails2.getId());
        assertEquals(post.getPostDetails().getDetails(), "postDetails2");
        assertEquals(postDetails.getDetails(), "postDetails2");
        assertEquals(post.getId(), postDetails.getId());
    }

    @Test
    public void one_to_one_is_unidirectional() {
        Post post = postRepository.getOne(post2.getId());
        assertNotNull(post.getPostDetails());
    }
}
