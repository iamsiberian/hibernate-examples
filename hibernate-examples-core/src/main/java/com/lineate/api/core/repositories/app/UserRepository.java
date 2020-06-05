package com.lineate.api.core.repositories.app;

import com.lineate.api.core.domain.app.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select 1 from User where email = :email")
    Integer checkEmail(String email);

    User findByEmail(String email);
}
