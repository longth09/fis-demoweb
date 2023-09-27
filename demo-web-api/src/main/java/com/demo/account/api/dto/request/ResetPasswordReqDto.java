package com.demo.account.api.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordReqDto {
    @NotEmpty
    String password;

    @NotEmpty
    String hash;
}
