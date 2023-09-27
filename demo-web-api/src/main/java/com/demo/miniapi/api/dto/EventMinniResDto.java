package com.demo.miniapi.api.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventMinniResDto {

    Long id;

    String name;

    String slug;

}
