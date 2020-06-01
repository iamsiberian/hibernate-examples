package com.lineate.api.core.repositories.onetoone.mapsid;

import com.lineate.api.core.domain.onetoone.mapsid.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select 1 from User where email = :email")
    Integer checkEmail(String email);

    User findByEmail(String email);
}
