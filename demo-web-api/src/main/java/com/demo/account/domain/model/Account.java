package com.demo.account.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.demo.shared.infrastructure.JsonAttributeConverter;
import com.demo.shared.model.JpaIDEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends JpaIDEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Convert(converter = JsonAttributeConverter.ListRoleDefinitionConverter.class)
    private List<RoleDefinition> roles;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "job_title")
    String jobTitle;

    String sector;

    String country;

    @Column(name = "want_to_join_workshop")
    boolean wantToJoinWorkshop;

    @Convert(converter = JsonAttributeConverter.ListStringConverter.class)
    List<String> interests;

    public void initBasicUserAccount() {
        this.setName(this.getFirstName() + " " + this.getLastName());
        this.setProvider(AuthProvider.local);
        this.setRoles(Collections.singletonList(RoleDefinition.ROLE_USER));
    }
}
