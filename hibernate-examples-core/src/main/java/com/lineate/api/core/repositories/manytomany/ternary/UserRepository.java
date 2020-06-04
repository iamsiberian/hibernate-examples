package com.lineate.api.core.repositories.manytomany.ternary;

import com.lineate.api.core.domain.manytomany.ternary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
