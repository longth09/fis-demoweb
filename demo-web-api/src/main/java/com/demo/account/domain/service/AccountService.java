package com.demo.account.domain.service;

import com.demo.account.api.dto.request.AccountRequestDto;
import com.demo.account.api.dto.request.ResetPasswordReqDto;
import com.demo.account.api.dto.request.RolesRequestDto;
import com.demo.account.domain.mapper.IAccountRequestMapper;
import com.demo.account.domain.model.Account;
import com.demo.account.domain.model.RoleDefinition;
import com.demo.account.infrastructure.repository.AccountRepository;
import com.demo.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.demo.common.exception.DefaultErrorCode.DEFAULT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository repository;
    private final IAccountRequestMapper mapper;

    @Override
    public Page<Account> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Account getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(DEFAULT_NOT_FOUND));
    }

    @Override
    public Account update(Long id, AccountRequestDto requestDto) {
        Account existed = this.getById(id);
        Account updateAccount = mapper.merge(existed, mapper.to(requestDto));
        return repository.save(updateAccount);
    }

    @Override
    public Account assignRoles(RolesRequestDto requestDto) {
        Account account = this.getById(requestDto.getId());
        appendRoles(requestDto, account);
        return repository.save(account);
    }

    @Override
    public Account removeRoles(RolesRequestDto requestDto) {
        Account account = this.getById(requestDto.getId());
        List<RoleDefinition> roles = account.getRoles();
        roles.removeIf(role -> requestDto.getRoles().contains(role));

        return repository.save(account);
    }

    @Override
    public Account deActive(Long id) {
        Account account = this.getById(id);
        account.setActive(false);
        return repository.save(account);
    }

    @Override
    public Account createIfNotExist(AccountRequestDto requestDto) {
        Optional<Account> optAccount = repository.findByEmail(requestDto.getEmail());
        if (optAccount.isPresent()) return optAccount.get();

        Account account = mapper.to(requestDto);
        account.initBasicUserAccount();

        return repository.save(account);
    }

    @Override
    public Account resetPassword(ResetPasswordReqDto requestDto) {
        return new Account();
    }

    private void appendRoles(RolesRequestDto requestDto, Account account) {
        List<RoleDefinition> roles = account.getRoles();
        roles.addAll(requestDto.getRoles());
        account.setRoles(roles);
    }
}
