package com.demo.coupons.domain.service;

import com.demo.common.exception.BusinessException;
import com.demo.coupons.domain.model.Coupons;
import com.demo.coupons.infrastructure.repository.CouponsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.demo.common.exception.DefaultErrorCode.DEFAULT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CouponsService implements ICouponsService {

    private final CouponsRepository couponsRepository;

    @Override
    public Page<Coupons> getAll(Pageable pageable) {
        return couponsRepository.findAll(pageable);
    }

    @Override
    public Coupons getById(Long id) {
        return couponsRepository.findById(id).orElseThrow(() -> new BusinessException(DEFAULT_NOT_FOUND));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            couponsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean clean() {
        try {
            couponsRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Coupons insert(Coupons coupons) {
        Optional<Coupons> coupons1 = couponsRepository.findById(coupons.getId());
        if (coupons1.isPresent()) return coupons1.get();

        coupons.setCurrentQuantity(coupons.getQuantity());
        return couponsRepository.save(coupons);
    }

    @Override
    public Coupons update(Long id, Coupons coupons) {
        coupons.setId(id);
        coupons.setCurrentQuantity(coupons.getQuantity());
        return couponsRepository.save(coupons);
    }

    @Override
    public List<String> listCodes() {
        return couponsRepository.listCodes();
    }

    @Override
    public Coupons findByCOde(String code) {
        return couponsRepository.findByCode(code);
    }

    @Override
    public Coupons updateQuantity(Long id, Integer quantity) {
        try{
            Coupons coupons = getById(id);
            coupons.setCurrentQuantity(coupons.getCurrentQuantity() - quantity);
            return couponsRepository.save(coupons);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRROORR");
            return null;
        }
    }
}
