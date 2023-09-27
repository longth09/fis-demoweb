package com.demo.account.api.controller;

import com.demo.account.api.dto.request.AccountRequestDto;
import com.demo.account.api.dto.request.ResetPasswordReqDto;
import com.demo.account.api.dto.request.RolesRequestDto;
import com.demo.account.domain.model.Account;
import com.demo.account.domain.service.IAccountService;
import com.demo.account.infrastructure.repository.AccountRepository;
import com.demo.common.exception.DefaultErrorCode;
import com.demo.common.rest.response.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;
    private final IAccountService service;


    @PatchMapping("/accounts/{id}")
    public BaseResponse<Account> update(@PathVariable(name = "id") Long id,
                                        @Valid @RequestBody AccountRequestDto requestDto) {
        return BaseResponse.ofSucceeded(service.update(id, requestDto));
    }

    @PostMapping("/accounts/add")
    public BaseResponse<Account> insert(@Valid @RequestBody AccountRequestDto requestDto) {
        return BaseResponse.ofSucceeded(service.createIfNotExist(requestDto));
    }

    @GetMapping("/accounts")
    public BaseResponse<Page<Account>> listAccount(Pageable pageable) {
        return BaseResponse.ofSucceeded(service.list(pageable));
    }

    @GetMapping("/accounts/{id}")
    public BaseResponse<Account> get(@PathVariable Long id) {
        Account account = service.getById(id);
        return BaseResponse.ofSucceeded(service.getById(id));
    }

    @PostMapping("/accounts/add/roles")
    public BaseResponse<Account> assignRoles(@Valid @RequestBody RolesRequestDto requestDto) {
        return BaseResponse.ofSucceeded(service.assignRoles(requestDto));
    }

    @PostMapping("/accounts/remove/roles")
    public BaseResponse<Account> removeRoles(@Valid @RequestBody RolesRequestDto requestDto) {
        return BaseResponse.ofSucceeded(service.removeRoles(requestDto));
    }

    @PostMapping("/accounts/de-active/{id}")
    public BaseResponse<?> deActive(@PathVariable Long id) {
        try {
            Account account = service.deActive(id);
            return BaseResponse.ofSucceeded(account);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_NOT_FOUND);
        }
    }

    @PostMapping("/accounts/reset-password")
    public BaseResponse<?> resetPassword(@RequestBody @Valid ResetPasswordReqDto reqDto) {
        try {
            Account account = service.resetPassword(reqDto);
            return BaseResponse.ofSucceeded(account);
        } catch (Exception e) {
            return BaseResponse.ofFailed(DefaultErrorCode.DEFAULT_BAD_REQUEST);
        }
    }

}
