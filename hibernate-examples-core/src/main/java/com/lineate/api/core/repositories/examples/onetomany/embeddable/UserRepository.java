package com.lineate.api.core.repositories.examples.onetomany.embeddable;

import com.lineate.api.core.domain.examples.onetomany.embeddable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
