package com.lineate.api.core.repositories.examples.onetoone.foreignkey;

import com.lineate.api.core.domain.examples.onetoone.foreignkey.Post;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static com.lineate.api.core.domain.examples.onetoone.foreignkey.ForeignKeyEntityUtils.createPost;
import static com.lineate.api.core.domain.examples.onetoone.foreignkey.ForeignKeyEntityUtils.createPostDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = TestConfigurationForForeignKey.class)
public class PostRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PostRepository postRepository;

    private Post post1;
    private Post post2;

    @BeforeEach
    public void setUp() {
        post1 = createPost("johndoe@gmail.com");
        post2 = createPost("sararay@gmail.com");
        post2.setPostDetails(createPostDetails(post2.getText()));
    }

    @Test
    public void should_find_no_post_if_repository_is_empty() {
        List<Post> posts = postRepository.findAll();

        assertEquals(0, posts.size());
    }

    @Test
    public void should_store_a_post() {
        Post post2AfterSave = postRepository.save(post2);

        assertNotNull(post2AfterSave.getId());
        assertEquals(post2AfterSave, post2);
    }

    @Test
    public void should_store_a_post_with_empty_post_details() {
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            postRepository.save(post1);
        });
    }
}
