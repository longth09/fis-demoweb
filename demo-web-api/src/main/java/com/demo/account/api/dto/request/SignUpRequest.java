package com.demo.account.api.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotEmpty
    String firstName;

    @NotEmpty
    String lastName;

    @Email
    private String email;

    @NotEmpty
    private String password;

    String companyName;

    String jobTitle;

    String sector;

    String country;

    List<String> interests;

    boolean wantToJoinWorkshop;

}
