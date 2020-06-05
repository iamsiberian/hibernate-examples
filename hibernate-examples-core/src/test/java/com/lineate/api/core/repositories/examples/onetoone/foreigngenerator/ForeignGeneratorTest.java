package com.lineate.api.core.repositories.examples.onetoone.foreigngenerator;

import com.lineate.api.core.domain.examples.onetoone.foreigngenerator.Post;
import com.lineate.api.core.domain.examples.onetoone.foreigngenerator.PostDetails;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.examples.onetoone.foreigngenerator.ForeignKeyEntityUtils.createPost;
import static com.lineate.api.core.domain.examples.onetoone.foreigngenerator.ForeignKeyEntityUtils.createPostDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes =TestConfigurationForForeignGenerator.class)
public class ForeignGeneratorTest extends BaseRepositoryTest {
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
        post2.setPostDetails(postDetails2);
        postDetails2.setPost(post2);
        entityManager.persist(post2);
    }

    @Test
    public void should_store_with_empty_post_details() {
        postRepository.save(post1);
    }

    @Test
    public void should_store() {
        post1.setPostDetails(postDetails1);
        postDetails1.setPost(post1);

        postRepository.save(post1);
    }

    @Test
    public void should_find_a_post_details() {
        Post post = postRepository.getOne(post2.getId());
        PostDetails postDetails = postDetailsRepository.getOne(postDetails2.getId());

        assertEquals(post.getPostDetails().getDetails(), "postDetails2");
        assertEquals(postDetails.getDetails(), "postDetails2");
        assertEquals(post.getId(), postDetails.getId());
    }
}
