package com.demo.account.domain.service;

import com.demo.account.api.dto.request.AccountRequestDto;
import com.demo.account.api.dto.request.ActiveAccountReqDto;
import com.demo.account.api.dto.request.ResetPasswordReqDto;
import com.demo.account.api.dto.request.RolesRequestDto;
import com.demo.account.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {
    Page<Account> list(Pageable pageable);

    Account getById(Long id);

    Account update(Long id, AccountRequestDto requestDto);

    Account assignRoles(RolesRequestDto requestDto);

    Account removeRoles(RolesRequestDto requestDto);

    Account deActive(Long id);

    Account createIfNotExist(AccountRequestDto requestDto);

    Account resetPassword(ResetPasswordReqDto requestDto);

}
