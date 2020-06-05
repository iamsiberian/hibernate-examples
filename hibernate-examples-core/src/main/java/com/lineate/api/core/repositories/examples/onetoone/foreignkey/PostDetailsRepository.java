package com.lineate.api.core.repositories.examples.onetoone.foreignkey;

import com.lineate.api.core.domain.examples.onetoone.foreignkey.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
