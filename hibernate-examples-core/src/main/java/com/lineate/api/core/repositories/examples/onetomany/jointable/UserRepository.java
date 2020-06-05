package com.lineate.api.core.repositories.examples.onetomany.jointable;

import com.lineate.api.core.domain.examples.onetomany.jointable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
