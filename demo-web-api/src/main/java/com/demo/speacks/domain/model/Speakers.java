package com.demo.speacks.domain.model;

import com.demo.event.domain.model.Event;
import com.demo.shared.model.JpaIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "speackers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Speakers extends JpaIDEntity {

    @Column(name = "title")
    String title;

    @Column(name = "img_url")
    String imgUrl;

    @Column(name = "description")
    String description;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "contact")
    String contact;

    @Column(name = "company")
    String company;

    @Column(name = "major")
    String major;

    @Column(name = "level")
    String level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event", referencedColumnName = "id")
    Event event;
}
