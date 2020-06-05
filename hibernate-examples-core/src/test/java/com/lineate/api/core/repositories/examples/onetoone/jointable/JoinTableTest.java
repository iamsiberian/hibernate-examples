package com.lineate.api.core.repositories.examples.onetoone.jointable;

import com.lineate.api.core.domain.examples.onetoone.jointable.Post;
import com.lineate.api.core.domain.examples.onetoone.jointable.PostDetails;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.examples.onetoone.jointable.JoinTableEntityUtils.createPost;
import static com.lineate.api.core.domain.examples.onetoone.jointable.JoinTableEntityUtils.createPostDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = TestConfigurationForJoinTable.class)
public class JoinTableTest extends BaseRepositoryTest {
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

        post2 = createPost("post2");
        postDetails2 = createPostDetails("postDetails2");
        entityManager.persist(postDetails2);
        post2.setPostDetails(postDetails2);
        entityManager.persist(post2);
    }

    @Test
    public void should_store_with_empty_post_details() {
        postDetailsRepository.save(postDetails1);
    }

    @Test
    public void should_store_with_empty_post() {
        postRepository.save(post1);
    }

    @Test
    public void should_store_a_post_details() {
        postDetailsRepository.save(postDetails1);
        post1.setPostDetails(postDetails1);
        postRepository.save(post1);
    }

    @Test
    public void should_find_post() {
        postRepository.save(post1);

        Post post1local = postRepository.getOne(post1.getId());
        assertNull(post1local.getPostDetails());

        Post post2local = postRepository.getOne(post2.getId());
        assertEquals(post2local.getPostDetails().getDetails(), postDetails2.getDetails());
    }
}
