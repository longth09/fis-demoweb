package com.demo.coupons.infrastructure.repository;

import com.demo.coupons.domain.model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons, Long> {
    @Query(value = "SELECT code FROM coupons", nativeQuery = true)
    List<String> listCodes();

    Coupons findByCode(String code);
}
