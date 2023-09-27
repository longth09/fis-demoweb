package com.demo.account.domain.mapper;

import com.demo.account.api.dto.request.AccountRequestDto;
import com.demo.account.domain.model.Account;
import com.demo.common.mapper.IObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IAccountRequestMapper extends IObjectMapper<Account, AccountRequestDto> {

    @Mapping(target = "id", ignore = true)
    Account merge(@MappingTarget Account target, Account source);

}
