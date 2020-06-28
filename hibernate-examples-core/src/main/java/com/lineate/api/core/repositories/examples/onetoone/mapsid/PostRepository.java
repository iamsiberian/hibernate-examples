package com.lineate.api.core.repositories.examples.onetoone.mapsid;

import com.lineate.api.core.domain.examples.onetoone.mapsid.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
