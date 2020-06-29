package com.lineate.api.core.repositories.examples.typedef;

import com.lineate.api.core.domain.examples.typedef.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
