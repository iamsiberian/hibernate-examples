package com.lineate.api.core.repositories.onetoone.sharedprimarykey;

import com.lineate.api.core.domain.onetoone.sharedprimarykey.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
