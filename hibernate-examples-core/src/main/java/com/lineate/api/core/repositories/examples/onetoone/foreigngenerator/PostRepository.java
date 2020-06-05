package com.lineate.api.core.repositories.examples.onetoone.foreigngenerator;

import com.lineate.api.core.domain.examples.onetoone.foreigngenerator.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
