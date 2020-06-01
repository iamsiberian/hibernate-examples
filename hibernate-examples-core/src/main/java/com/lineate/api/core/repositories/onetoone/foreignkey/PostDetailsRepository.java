package com.lineate.api.core.repositories.onetoone.foreignkey;

import com.lineate.api.core.domain.onetoone.foreignkey.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
