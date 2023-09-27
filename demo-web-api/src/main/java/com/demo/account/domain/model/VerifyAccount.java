package com.demo.account.domain.model;

import com.demo.shared.model.JpaIDEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "verify_account")
@Data
public class VerifyAccount extends JpaIDEntity {

    @Email
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private VerifyType type;

    private String checksum;
}
