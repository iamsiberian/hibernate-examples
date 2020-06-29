package com.lineate.api.core.repositories.examples.typedef;

import com.lineate.api.core.domain.examples.typedef.Book;
import com.lineate.api.core.domain.examples.typedef.User;
import com.lineate.api.core.repositories.BaseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static com.lineate.api.core.domain.examples.typedef.TypeDefEntityUtils.createAuthors;
import static com.lineate.api.core.domain.examples.typedef.TypeDefEntityUtils.createBook;
import static com.lineate.api.core.domain.examples.typedef.TypeDefEntityUtils.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ContextConfiguration(classes = TestConfigurationForTypeDef.class)
public class BookRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    BookRepository repository;

    private User user1;
    private User user1afterSave;
    private User user2;
    private User user2afterSave;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        user1 = createUser("johndoe@gmail.com", "hashpass", "John Doe");
        user2 = createUser("sararay@gmail.com", "abrakadabra", "Sara Ray");
        book1 = createBook("Непрерывное развитие API", createAuthors("Меджуи", "Уайлд", "Митра"), null);
        book2 = createBook("Чистая архитектура", createAuthors("Мартин"), null);
    }

    public void persistBooks() {
        user1afterSave = entityManager.persist(user1);
        user2afterSave = entityManager.persist(user2);
        book1.setUser(user1afterSave);
        entityManager.persist(book1);
        book2.setUser(user2afterSave);
        entityManager.persist(book2);
    }

    @Test
    public void should_find_no_books_if_repository_is_empty() {
        List<Book> users = repository.findAll();

        assertThat(users).isEmpty();
    }

    @Test
    public void should_store_a_book() {
        User user1afterSave = entityManager.persist(user1);
        book1.setUser(user1afterSave);
        Book bookAfterSave = repository.save(book1);

        assertThat(bookAfterSave).isEqualTo(book1);
    }

    @Test
    public void should_delete_all_books() {
        persistBooks();

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void should_find_all_books() {
        persistBooks();

        Iterable<Book> users = repository.findAll();

        assertThat(users).hasSize(2).contains(book1, book2);
    }

    @Test
    public void should_find_book_by_id() {
        persistBooks();

        Optional<Book> foundBook = repository.findById(book2.getId());

        if (foundBook.isPresent()) {
            assertThat(foundBook.get()).isEqualTo(book2);
        } else {
            fail("Book2 not found");
        }
    }
}
