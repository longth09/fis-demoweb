package com.demo.speacks.api.dto;

import com.demo.event.domain.model.Event;
import com.demo.shared.model.JpaIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpeackerEventDto extends JpaIDEntity {

    String title;

    String imgUrl;

    String description;

    String phoneNumber;

    String contact;

    String company;

    String major;

    String level;

    Event event;

    Long idEvent;

    String nameEvent;

}
