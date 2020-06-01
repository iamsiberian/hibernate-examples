package com.lineate.api.core.repositories.onetoone.foreigngenerator;

import com.lineate.api.core.domain.onetoone.foreigngenerator.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
