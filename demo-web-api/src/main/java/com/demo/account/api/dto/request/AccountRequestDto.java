package com.demo.account.api.dto.request;

import com.demo.account.domain.model.AuthProvider;
import com.demo.account.domain.model.RoleDefinition;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {

    @NotEmpty
    String firstName;

    @NotEmpty
    String lastName;

    @Email
    String email;

    String name;

    String imageUrl;

    AuthProvider provider;

    String providerId;

    List<RoleDefinition> roles;

    boolean isActive;

    String companyName;

    String jobTitle;

    String sector;

    String country;

    List<String> interests;

    boolean wantToJoinWorkshop;

}
