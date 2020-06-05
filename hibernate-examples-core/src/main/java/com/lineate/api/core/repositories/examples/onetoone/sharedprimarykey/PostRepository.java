package com.lineate.api.core.repositories.examples.onetoone.sharedprimarykey;

import com.lineate.api.core.domain.examples.onetoone.sharedprimarykey.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
