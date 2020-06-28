package com.lineate.api.core.repositories.examples.onetoone.mapsid;

import com.lineate.api.core.domain.examples.onetoone.mapsid.Post;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static com.lineate.api.core.domain.examples.onetoone.mapsid.MapsIdEntityUtils.createPost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ContextConfiguration(classes = TestConfigurationForMapsId.class)
public class PostRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PostRepository repository;

    private Post post1;
    private Post post2;

    @BeforeEach
    public void setUp() {
        post1 = createPost("post1");
        post2 = createPost("post2");
    }

    private void assertPost(Post actual, Post expected) {
        assertThat(actual).hasFieldOrProperty("id");
        assertThat(actual).hasFieldOrPropertyWithValue("text", expected.getText());
    }

    @Test
    public void should_find_no_posts_if_repository_is_empty() {
        List<Post> posts = repository.findAll();

        assertThat(posts).isEmpty();
    }

    @Test
    public void should_store_a_post() {
        Post postAfterSave = repository.save(post1);

        assertPost(postAfterSave, post1);
    }

    @Test
    public void should_delete_all_post() {
        entityManager.persist(post1);
        entityManager.persist(post2);

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void should_find_all_posts() {
        entityManager.persist(post1);
        entityManager.persist(post2);

        Iterable<Post> users = repository.findAll();

        assertThat(users).hasSize(2).contains(post1, post2);
    }

    @Test
    public void should_find_post_by_id() {
        entityManager.persist(post1);
        entityManager.persist(post2);

        Optional<Post> foundCustomer = repository.findById(post2.getId());

        if (foundCustomer.isPresent()) {
            assertThat(foundCustomer.get()).isEqualTo(post2);
        } else {
            fail("Post2 not found");
        }
    }
}
