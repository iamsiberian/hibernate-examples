package com.lineate.api.core.repositories.onetoone.foreignkey;

import com.lineate.api.core.domain.onetoone.foreignkey.Post;
import com.lineate.api.core.domain.onetoone.foreignkey.PostDetails;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static com.lineate.api.core.domain.onetoone.foreignkey.ForeignKeyEntityUtils.createPost;
import static com.lineate.api.core.domain.onetoone.foreignkey.ForeignKeyEntityUtils.createPostDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = TestConfigurationForForeignKey.class)
public class PostDetailsRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PostDetailsRepository postDetailsRepository;
    @Autowired
    private PostRepository postRepository;

    private Post post1;
    private Post post2;
    private PostDetails postDetails1;

    @BeforeEach
    public void setUp() {
        post1 = createPost("johndoe@gmail.com");
        postDetails1 = createPostDetails(post1.getText());
        post1.setPostDetails(postDetails1);

        entityManager.persist(post1);
    }

    @Test
    public void should_store_a_post_details() {
        Long POST_ID = post1.getId();
        Long POST_DETAILS_ID = postDetails1.getId();

        Post post1local = postRepository.getOne(POST_ID);
        assertEquals(post1local.getPostDetails().getDetails(), "johndoe@gmail.com");

        PostDetails post1detailsLocal = postDetailsRepository.getOne(POST_DETAILS_ID);

        assertEquals(post1detailsLocal.getDetails(), "johndoe@gmail.com");
    }

    @Test
    public void should_store_a_non_unique_relationship() {
        post2 = createPost("sararay@gmail.com");
        post2.setPostDetails(postDetails1);

        entityManager.persist(post2);
        assertThrows(javax.persistence.PersistenceException.class, () -> {
            entityManager.flush();
        });
    }
}
