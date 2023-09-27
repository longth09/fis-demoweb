package com.demo.event.api.dto;

import com.demo.shared.model.TimestampEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventResminiDto extends TimestampEntity {

    Long id;

    String name;

    String slug;

}
