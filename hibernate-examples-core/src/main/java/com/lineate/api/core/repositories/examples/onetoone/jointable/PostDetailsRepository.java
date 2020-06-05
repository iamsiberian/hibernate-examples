package com.lineate.api.core.repositories.examples.onetoone.jointable;

import com.lineate.api.core.domain.examples.onetoone.jointable.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
