package com.demo.coupons.domain.service;

import com.demo.coupons.domain.model.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICouponsService {

    Page<Coupons> getAll(Pageable pageable);

    Coupons getById(Long id);

    Boolean delete(Long id);

    Boolean clean();

    Coupons insert(Coupons coupons);

    Coupons update(Long id, Coupons coupons);

}
