package com.lineate.api.core.repositories.onetoone.sharedprimarykey;

import com.lineate.api.core.domain.onetoone.sharedprimarykey.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
