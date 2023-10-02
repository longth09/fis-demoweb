package com.demo.account.infrastructure.repository;

import com.demo.account.api.dto.request.SignInRequest;
import com.demo.account.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT email, password, token, roles FROM account", nativeQuery = true)
    Account getAccountLogin();

    @Query(value = "SELECT a FROM Account a where a.email = :email and a.password = :passwork")
    Account checkLogin(@Param("email") String email, @Param("passwork") String passwork);

}
