package com.lineate.api.core.repositories.examples.onetoone.mapsid;

import com.lineate.api.core.domain.examples.onetoone.mapsid.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {
}
