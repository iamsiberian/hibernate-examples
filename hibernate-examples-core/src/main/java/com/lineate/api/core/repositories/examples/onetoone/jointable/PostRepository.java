package com.lineate.api.core.repositories.examples.onetoone.jointable;

import com.lineate.api.core.domain.examples.onetoone.jointable.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
