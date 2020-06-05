package com.lineate.api.core.repositories.examples.onetoone.foreignkey;

import com.lineate.api.core.domain.examples.onetoone.foreignkey.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
