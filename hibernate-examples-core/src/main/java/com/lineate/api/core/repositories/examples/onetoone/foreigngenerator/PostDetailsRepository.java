package com.lineate.api.core.repositories.examples.onetoone.foreigngenerator;

import com.lineate.api.core.domain.examples.onetoone.foreigngenerator.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
