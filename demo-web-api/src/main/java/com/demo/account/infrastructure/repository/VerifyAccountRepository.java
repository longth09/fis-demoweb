package com.demo.account.infrastructure.repository;

import com.demo.account.domain.model.VerifyAccount;
import com.demo.account.domain.model.VerifyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface VerifyAccountRepository extends JpaRepository<VerifyAccount, Long> {
    VerifyAccount findByTypeAndChecksumAndCreatedDateGreaterThan(VerifyType type, String checksum, Instant datetime);

}
