package com.lineate.api.core.repositories.examples.onetoone.sharedprimarykey;

import com.lineate.api.core.domain.examples.onetoone.sharedprimarykey.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
