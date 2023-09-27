package com.demo.account.api.dto.request;

import com.demo.account.domain.model.RoleDefinition;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RolesRequestDto {

    Long id;

    List<RoleDefinition> roles;
}
