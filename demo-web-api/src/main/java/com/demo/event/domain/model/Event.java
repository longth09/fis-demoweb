package com.demo.event.domain.model;

import com.demo.shared.model.JpaIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "events", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event extends JpaIDEntity implements Serializable {

    @Column(nullable = false, unique = true)
    @NotEmpty
    String name;

    @NotBlank(message = "Slug is null?")
    String slug;

    @Column(name = "`desc`")
    String desc;

    @Column(name = "`start_time`")
    Instant startTime;

    @Column(nullable = false)
    String location;

    String imgUrl;

    Boolean isActive;
}
