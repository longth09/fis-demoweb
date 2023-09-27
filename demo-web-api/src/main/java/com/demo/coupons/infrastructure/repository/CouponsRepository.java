package com.demo.coupons.infrastructure.repository;

import com.demo.coupons.domain.model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons, Long> {
}
