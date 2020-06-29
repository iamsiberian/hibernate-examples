package com.lineate.api.core.repositories.examples.typedef;

import com.lineate.api.core.domain.examples.typedef.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
