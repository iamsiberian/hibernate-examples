package com.lineate.api.core.repositories.onetoone.foreigngenerator;

import com.lineate.api.core.domain.onetoone.foreigngenerator.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
