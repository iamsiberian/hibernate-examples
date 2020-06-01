package com.lineate.api.core.repositories.onetoone.jointable;

import com.lineate.api.core.domain.onetoone.jointable.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
