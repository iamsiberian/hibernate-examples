package com.lineate.api.core.repositories.onetomany.embeddable;

import com.lineate.api.core.domain.onetomany.embeddable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
