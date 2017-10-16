package com.opencondo.pollservice.domain.repository;

import com.opencondo.pollservice.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserAccountRepository  extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findById(Long id);

    Optional<UserAccount> findByUsername(String username);

}
