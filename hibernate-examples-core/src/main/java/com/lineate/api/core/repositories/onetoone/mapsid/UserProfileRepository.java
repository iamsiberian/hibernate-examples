package com.lineate.api.core.repositories.onetoone.mapsid;

import com.lineate.api.core.domain.onetoone.mapsid.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
