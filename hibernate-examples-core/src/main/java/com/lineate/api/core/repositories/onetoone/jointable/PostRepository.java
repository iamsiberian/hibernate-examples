package com.lineate.api.core.repositories.onetoone.jointable;

import com.lineate.api.core.domain.onetoone.jointable.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
