package com.lineate.api.core.repositories.onetomany.jointable;

import com.lineate.api.core.domain.onetomany.jointable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
