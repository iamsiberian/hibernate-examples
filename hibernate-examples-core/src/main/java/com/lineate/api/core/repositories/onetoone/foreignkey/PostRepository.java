package com.lineate.api.core.repositories.onetoone.foreignkey;

import com.lineate.api.core.domain.onetoone.foreignkey.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
