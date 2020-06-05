package com.lineate.api.core.repositories.examples.maps.ternary;

import com.lineate.api.core.domain.examples.maps.ternary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
