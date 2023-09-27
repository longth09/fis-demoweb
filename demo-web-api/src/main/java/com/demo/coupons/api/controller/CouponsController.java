package com.demo.coupons.api.controller;

import com.demo.common.exception.DefaultErrorCode;
import com.demo.common.rest.response.BaseResponse;
import com.demo.coupons.domain.model.Coupons;
import com.demo.coupons.domain.service.ICouponsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CouponsController {

    private final ICouponsService iCouponsService;

    @GetMapping("coupons")
    BaseResponse<Page<Coupons>> getAll(Pageable pageable) {
        return BaseResponse.ofSucceeded(iCouponsService.getAll(pageable));
    }

    @GetMapping("coupons/{id}")
    BaseResponse<?> findById(@PathVariable("id") Long id) {
        Coupons coupons = iCouponsService.getById(id);
        if (coupons != null) return BaseResponse.ofSucceeded(iCouponsService.getById(id));
        return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
    }

    @PutMapping("coupons/{id}")
    BaseResponse<?> update(@Valid @RequestBody Coupons coupons, @PathVariable("id") Long id) {
        try {
            Coupons coupons1 = iCouponsService.update(id, coupons);
            return BaseResponse.ofSucceeded(coupons1);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
        }
    }

    @PostMapping("coupons")
    BaseResponse<?> insert(@Valid @RequestBody Coupons coupons) {
        try {
            Coupons coupons1 = iCouponsService.insert(coupons);
            return BaseResponse.ofSucceeded(coupons1);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
        }
    }

    @DeleteMapping("coupons/remove/{id}")
    public BaseResponse<?> remove(@PathVariable("id") Long id) {
        Boolean del = iCouponsService.delete(id);
        if (del) return BaseResponse.ofSucceeded("");
        return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
    }

    @DeleteMapping("coupons/remove")
    public BaseResponse<?> removeAll() {
        Boolean delAll = iCouponsService.clean();
        if (delAll) return BaseResponse.ofSucceeded("");
        return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
    }
}
